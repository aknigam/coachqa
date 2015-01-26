package com.coachqa.repository.db.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

public class SpringDAOCodeGenerationTool 
{
	private String packageName = "com.expedia";

	private String folderName ; 

	private boolean isLogEnabled;

	private boolean isWriteCodeToFile = true;

	private MetadataReader metadataReader ;

	private Properties configProp = new Properties();

	public static void main(String[] args) throws Exception
	{
		SpringDAOCodeGenerationTool tool = new SpringDAOCodeGenerationTool();
		tool.init();
		tool.readSprocsAndGenerateCode();
	}


	public void readSprocsAndGenerateCode() throws Exception
	{

		List<StoredProcedure> sprocs = metadataReader.getStoredProcedures();

		for (StoredProcedure sproc : sprocs)
		{
			generateCode(sproc);
		}

	}


	public String generateCode(StoredProcedure sproc) throws IOException{

		StringBuffer code = new StringBuffer();
		addLine(code, "","package "+ packageName, ";");
		addBlankLine(code);

		List<String> imports = getImports();


		for (String importStr : imports)
		{
			addLine(code, importStr);
		}
		addBlankLine(code);

		addLine(code, "public class "+ getClassName(sproc) );
		addLine(code, "{");

		addLine(code, "\t", "private SimpleJdbcCall "+getSimpleJDBCVariableName(sproc), ";");
		addBlankLine(code);

		// add constants start
		List<SprocColumns> cols = sproc.getColumns();
		for (SprocColumns col : cols)
		{
			String colName = col.getColName();
			addLine(code, "\t", "private static String "+getColumnConstant(colName), "\t\t = \""+colName+"\";");	
		}
		addBlankLine(code);
		// add constants end
		addBlankLine(code);

		addLine(code, "\t", "public "+getClassName(sproc)+"(DataSource m_dataSource)", "");
		addLine(code, "\t{");

		addLine(code, "\t\t", getSimpleJDBCVariableName(sproc)+" = new SimpleJdbcCall(m_dataSource)", "");
		addLine(code, "\t\t", ".withProcedureName(\""+sproc.getSprocName()+"\")", "");
		addLine(code, "\t\t", ".withCatalogName(\""+sproc.getSchemaName()+"\")", "");
		addLine(code, "\t\t", ".withoutProcedureColumnMetaDataAccess()", "");
		if(cols== null || cols.size() == 0)
		{
			addLine(code, ";");
		}

		List<SprocColumns> inCols = sproc.getInColumns();
		if(inCols != null && inCols.size()>0)
		{
			int length = inCols.size();
			addLine(code, "\t\t", ".useInParameterNames(", "");
			for (int i = 0; i < length; i++)
			{
				SprocColumns col = inCols.get(i);
				String colName = col.getColName();
				if(i == length -1)
					addLine(code, "\t\t\t", getColumnConstant(colName), "");
				else
					addLine(code, "\t\t\t", getColumnConstant(colName), ",");

			}
			addLine(code, "\t\t", ")", "");

		}
		if(cols!= null && cols.size()>0)
		{
			addLine(code, "\t\t", ".declareParameters(", "");
			int length = cols.size();
			Collections.sort(cols);
			for (int i = 0; i < length; i++)
			{
				SprocColumns col = cols.get(i);
				String dataType = TypeFactory.getType(col.getColumnDataType());
				String sqlParamClassType = getSqlParamClassType(col.getColumnReturn());
				if(sqlParamClassType == null)
					continue;
				if(i == length -1)
					addLine(code, "\t\t\t", "new "+sqlParamClassType+"("+getColumnConstant(col.getColName())+"\t,"+dataType+")", "");
				else
					addLine(code, "\t\t\t", "new "+sqlParamClassType+"("+getColumnConstant(col.getColName())+"\t,"+dataType+"),", "");

			}

			addLine(code, "\t\t", ")", ";");
		}

		addLine(code, "\t}");

		// end class tag
		addLine(code, "}");

		if(isLogEnabled)
			System.out.println(code);

		String filename = getClassName(sproc)+".java";
		if(isWriteCodeToFile)
			writeToFile(code, filename);

		return code.toString();
	}



	private String getSqlParamClassType(int columnReturn)
	{
		if(columnReturn == DatabaseMetaData.procedureColumnIn){
			return SqlParameter.class.getSimpleName();
		}
		else if(columnReturn == DatabaseMetaData.procedureColumnInOut){
			return SqlInOutParameter.class.getSimpleName();	
		}
		else if(columnReturn == DatabaseMetaData.procedureColumnOut){
			return SqlOutParameter.class.getSimpleName();
		}
		return null;
	}


	private String getColumnConstant(String colName)
	{
		return "P_"+colName.substring(0, colName.length()).toUpperCase();
	}


	private List<String> getImports()
	{
		List<String> imports = new ArrayList<>();
		imports.add("import javax.sql.DataSource;");
		imports.add("import java.sql.Types;");
		imports.add("");
		imports.add("import org.springframework.jdbc.core.SqlParameter;");
		imports.add("import org.springframework.jdbc.core.SqlOutParameter;");
		imports.add("import org.springframework.jdbc.core.SqlInOutParameter;");
		imports.add("import org.springframework.jdbc.core.simple.SimpleJdbcCall;");
		return imports;
	}

	private void writeToFile(StringBuffer code, String filename) throws IOException
	{
		String packagepath = packageName.replace(".", "/");
		String[] packagepathElements = packagepath.split("/");
		String pathToFile = folderName;
		for (String path : packagepathElements)
		{
			File file = new File(pathToFile+"/"+path);
			if(!file.exists())
			{
				file.mkdir();
			}	
			pathToFile = pathToFile+"/"+path;
		}
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(folderName+packagepath+"/"+filename));)
		{
			writer.write(code.toString());
			writer.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private String getSimpleJDBCVariableName(StoredProcedure sproc)
	{
		return "m_"+getClassName(sproc)+"Sproc";
	}


	private String getClassName(StoredProcedure sproc)
	{
		return sproc.getSprocName().replace("#", "");
	}


	private void addLine(StringBuffer code, String line)
	{
		addLine(code, "", line, "");
	}

	private void addLine(StringBuffer code, String start, String line, String end)
	{
		if(line == null)
			addBlankLine(code);
		else
		{
			if(start!=null)	code.append(start);
			code.append(line);
			if(start!=null)	code.append(end);
			addBlankLine(code);
		}
	}

	private void addBlankLine(StringBuffer buffer){
		buffer.append("\n");
	}


	public void init(){
		InputStream in = this.getClass().getResourceAsStream("codegen.properties");
		try {
			configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

		folderName =  configProp.getProperty("code.src.folder");
		packageName =  configProp.getProperty("package.name");
		isWriteCodeToFile =  getBooleanValue(configProp.getProperty("generate.code"));
		isLogEnabled =  getBooleanValue(configProp.getProperty("sysout.code"));


		String schemaName = configProp.getProperty("schema.name");
		String sprocName = configProp.getProperty("procedure.name");

		schemaName = schemaName.isEmpty()?null:schemaName;
		sprocName = sprocName.isEmpty()?null:sprocName;

		String dbConnectionURL = configProp.getProperty("db.connection.url");
		String dbUsername = configProp.getProperty("db.connection.username");
		String dbPassword = configProp.getProperty("db.connection.password");

		metadataReader = new MetadataReader(schemaName, sprocName, dbConnectionURL, dbUsername, dbPassword);
	}

	private boolean getBooleanValue(String property)
	{
		if(property == null || property.isEmpty())
			return true;

		if(property.equalsIgnoreCase("true"))
			return true;

		if(property.equalsIgnoreCase("false"))
			return false;

		return false;
	}
}
