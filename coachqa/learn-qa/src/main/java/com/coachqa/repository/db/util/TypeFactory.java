package com.coachqa.repository.db.util;

import java.lang.reflect.Field;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class TypeFactory
{

	private static Map<Integer, String> typesValueNameMap = new HashMap<>();
	static
	{
		try{

			Field[] fields = Types.class.getDeclaredFields();
			for (Field field : fields)
			{
				String name = field.getName();
				Integer value = field.getInt(null);
				typesValueNameMap.put(value, name);
			}
		}catch(Exception e){
			
		}
	}
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException
	{
		
		System.out.println(typesValueNameMap);
		
	}
	
	public static String getType(Integer dataType){
		return "Types."+typesValueNameMap.get(dataType);
	}
}
