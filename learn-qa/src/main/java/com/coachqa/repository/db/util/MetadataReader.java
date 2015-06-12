package com.coachqa.repository.db.util;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Refer : http://stackoverflow.com/questions/3854192/java-read-metadata-from-procedure
 * 
 * TODO anigam Describe MetadataReader
 * 
 * @author <a href="mailto:anigam@expedia.com">anigam</a>
 *
 */
public class MetadataReader
{

	public static void main(String[] args) throws Exception {

//		List<StoredProcedure> procedures= 		getStoredProcedures();
	}


	private String schemaName;
	private String sprocname;
	private String dbConnectionURL;
	private String dbUsername;
	private String dbPassword;

	public MetadataReader(String schemaName, String sprocName, String dbConnectionURL, String dbUsername, String dbPassword)
	{
		this.schemaName = schemaName;
		this.sprocname = sprocName;
		this.dbConnectionURL = dbConnectionURL;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		
	}

	public List<StoredProcedure> getStoredProcedures() throws Exception
	{


		// catalog . schema . proc name . list of columns
		Map<String, Map<String, Map<String ,StoredProcedure>>> sprocs = new HashMap<>();

		List<StoredProcedure> procedures = new ArrayList<>();


		Connection conn =null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			conn = getSQLServerConnection();
			System.out.println("Got Connection.");
			st = conn.createStatement();

			DatabaseMetaData dbMetaData = conn.getMetaData();
			rs = dbMetaData.getProcedureColumns(conn.getCatalog(),
					schemaName,
					sprocname,//"SterlingMemberGetByUser#1",
					null);

			while(rs.next()) {
				// get stored procedure metadata

				//			System.out.println("-----------------------------------------------------------------------------");
				String procedureCatalog     = rs.getString(1);
				String procedureSchema      = rs.getString(2);
				String procedureName        = rs.getString(3);
				String columnName           = rs.getString(4);
				short  columnReturn         = rs.getShort(5); // return type IN OUT
				int    columnDataType       = rs.getInt(6);
//				String columnReturnTypeName = rs.getString(7);
//				int    columnPrecision      = rs.getInt(8);
//				int    columnByteLength     = rs.getInt(9);
//				short  columnScale          = rs.getShort(10);
//				short  columnRadix          = rs.getShort(11);
//				short  columnNullable       = rs.getShort(12);
//				String columnRemarks        = rs.getString(13);

				Map<String, Map<String, StoredProcedure>>  catalog = sprocs.get(procedureCatalog);
				if(catalog== null)
				{
					catalog = new HashMap<>();
					sprocs.put(procedureCatalog, catalog);
				}
				Map<String, StoredProcedure> schema = catalog.get(procedureSchema);
				if(schema == null)
				{
					schema = new HashMap<>();
					catalog.put(procedureSchema, schema);
				}
				StoredProcedure sproc = schema.get(procedureName);
				if(sproc == null)
				{
					sproc =  new StoredProcedure(procedureName,procedureCatalog, procedureSchema);
					procedures.add(sproc);
					schema.put(procedureName, sproc);
				}
				sproc.addColumn(columnName, columnDataType, columnReturn);
				//			System.out.println("stored Procedure name="+procedureName);
				//			System.out.println("procedureCatalog=" + procedureCatalog);
				//			System.out.println("procedureSchema=" + procedureSchema);
				//			System.out.println("procedureName=" + procedureName);
				//			System.out.println("columnName=" + columnName);
				//			System.out.println("columnReturn=" + columnReturn);
				//			System.out.println("columnDataType=" + columnDataType);
				//			System.out.println("columnReturnTypeName=" + columnReturnTypeName);
				//			System.out.println("columnPrecision=" + columnPrecision);
				//			System.out.println("columnByteLength=" + columnByteLength);
				//			System.out.println("columnScale=" + columnScale);
				//			System.out.println("columnRadix=" + columnRadix);
				//			System.out.println("columnNullable=" + columnNullable);
				//			System.out.println("columnRemarks=" + columnRemarks);
				//			break;
			}

			//		print(sprocs);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			rs.close();
			st.close();
			conn.close();
		}

		return procedures;
	}


	private static void print(Map<String, Map<String, Map<String, StoredProcedure>>> sprocs)
	{

		for(Entry<String, Map<String, Map<String, StoredProcedure>>> catalog: sprocs.entrySet())
		{
			String catalogName = catalog.getKey();
			System.out.println(catalogName);
			Map<String, Map<String, StoredProcedure>> schema = catalog.getValue();

			Set<Entry<String, Map<String, StoredProcedure>>> sproc = schema.entrySet();

			for (Entry<String, Map<String, StoredProcedure>> entry : sproc)
			{
				String schemaname = entry.getKey();
				System.out.println("\t\t"+schemaname);
				Map<String, StoredProcedure> procedures = entry.getValue();

				Set<Entry<String, StoredProcedure>> procentries = procedures.entrySet();

				for (Entry<String, StoredProcedure> entry2 : procentries)
				{
					System.out.println("----------------------------------------------------------");
					String procName = entry2.getKey();
					System.out.println("\t\t\t"+procName);
					StoredProcedure cols = entry2.getValue();
					System.out.println("\t\t\t{");
					for (SprocColumns sprocColumns : cols.getColumns())
					{
						System.out.println(sprocColumns);
					}
					System.out.println("\t\t\t}");

				}
			}


		}
	}


	public Connection getSQLServerConnection() throws Exception {

		String driver = "com.mysql.jdbc.Driver";
		String url = dbConnectionURL;
		String username = dbUsername;
		String password = dbPassword;

		Class.forName(driver); // load Oracle driver
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
}

class StoredProcedure{
	private String m_catalogName; 
	private String m_schemaName; 
	private String m_sprocName; 
	private List<SprocColumns> columns = new ArrayList<>();
	private List<SprocColumns> inColumns = new ArrayList<>();
	private List<SprocColumns> inOutColumns = new ArrayList<>();
	private List<SprocColumns> outColumns = new ArrayList<>();

	public StoredProcedure(String sprocName, String catalogName, String schemaName)
	{
		m_sprocName = sprocName;
		m_catalogName = catalogName;
		m_schemaName = schemaName;
	}

	public void addColumn(String columnName, int columnDataType, short columnReturn){
		
		SprocColumns col= new SprocColumns(columnName, columnDataType, columnReturn);
		if(columnReturn == DatabaseMetaData.procedureColumnIn){
			inColumns.add(col);
			columns.add(col);
		}
		else if(columnReturn == DatabaseMetaData.procedureColumnInOut){
			inOutColumns.add(col);
			columns.add(col);
		}
		else if(columnReturn == DatabaseMetaData.procedureColumnOut){
			outColumns.add(col);
			columns.add(col);
		}
		
		
	}

	public List<SprocColumns> getColumns()
	{
		return columns;
	}

	public String getCatalogName()
	{
		return m_catalogName;
	}

	public String getSchemaName()
	{
		return m_schemaName;
	}

	public String getSprocName()
	{
		return m_sprocName;
	}

	public List<SprocColumns> getInColumns()
	{
		return inColumns;
	}

	public List<SprocColumns> getInOutColumns()
	{
		return inOutColumns;
	}

	public List<SprocColumns> getOutColumns()
	{
		return outColumns;
	}
}
class SprocColumns implements Comparable<SprocColumns>{

	private String columnName;
	private String colName;
	private int columnDataType;
	private short columnReturn;

	public SprocColumns(String columnName, int columnDataType, short columnReturn)
	{
		this.columnName = columnName;
		this.columnDataType = columnDataType;
		this.columnReturn = columnReturn;
		this.colName = columnName.substring(1, columnName.length());
	}

	@Override
	public String toString()
	{
		return "\t\t\t\t"+columnName+"\t"+columnDataType +"\t"+ TypeFactory.getType(Short.valueOf(columnReturn).intValue());
	}

	public String getColumnName()
	{
		return columnName;
	}

	public int getColumnDataType()
	{
		return columnDataType;
	}

	public short getColumnReturn()
	{
		return columnReturn;
	}

	public String getColName()
	{
		return colName;
	}
	
	@Override
	public int compareTo(SprocColumns o)
	{
		if(o.columnDataType >= columnDataType)
			return 1;
		return -1;
	}
	
	

}