package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DBInfo {
	public static final String ClassName;
	public static final String ConStr;
	public static final String User;
	public static final String Pass;
	
	static{
	    Properties properties = new Properties();
	    String strpass = "/WhisperBBS/properties/db.properties";    
	    try {
	        InputStream istream = new FileInputStream(strpass);
	        properties.load(istream);
				User = properties.getProperty("Id");
				Pass = properties.getProperty("Pass");
				ClassName = properties.getProperty("Class_Name");
				ConStr = properties.getProperty("Connection_String");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println(ClassName);
	    System.out.println(ConStr);
	    System.out.println(User);
	    System.out.println(Pass);
	}
}
