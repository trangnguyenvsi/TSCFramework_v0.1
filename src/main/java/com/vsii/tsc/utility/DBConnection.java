package com.vsii.tsc.utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
static Properties p;	
	public static ResultSet connectSQLServer(String sqlCommandName) throws IOException{
		
		p = CommonOperations.readConfig();
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sqlCommand;
		
		//Defining the SQL URL
		String dbAddress = p.getProperty("SQLServer_address");
		String dbName = p.getProperty("SQLServer_dbName");
		String user = p.getProperty("SQLServer_user");
		String pwd = p.getProperty("SQLServer_pwd");
		
		//Define sql command
		sqlCommand = p.getProperty(sqlCommandName);
		
		//Driver of SQLServer
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String connection = "jdbc:sqlserver://"+dbAddress+";databaseName="+dbName+";user="+user+";password="+pwd;
		//Perform connection
		try {
		//Loading the driver and creating its instance
		Class.forName(driver).newInstance();
		//Establishing the connection with the database
		
		conn = DriverManager.getConnection(connection);
		/*createStatement() method creates a Statement object for sending SQL to the database.
		*It executes the SQL and returns the result it produces*/	
		 stmt = conn.createStatement();                      
		 /*executeQuery() method executes the SQL statement which returns a single ResultSet type object.*/
		 rs =  stmt.executeQuery(sqlCommand);
		}
		catch(Exception e){
			System.out.println("Couldn't connect to DB");
			
		}
		return rs;

	}
public static ResultSet connectMySQL(String sqlCommandName) throws IOException{
		
	 p = CommonOperations.readConfig();
	/*Connection is an interface which helps you establish connection with database.*/
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	String sqlCommand;
	
	//Defining the SQL properties conneciton
	String dbAddress = p.getProperty("MySQL_address");
	String dbName = p.getProperty("MySQL_dbName");
	String user = p.getProperty("MySQL_user");
	String pwd = p.getProperty("MySQL_pwd");
	
	//Defind sql command
	sqlCommand = p.getProperty(sqlCommandName);
	
	//Driver of SQLServer
	String driver = "com.mysql.jdbc.Driver";

	//Perform connection
	try {
	//Loading the driver and creating its instance
	Class.forName(driver).newInstance();

	//Establishing the connection with the database
	conn = DriverManager.getConnection(dbAddress+dbName,user,pwd);
	
	/*createStatement() method creates a Statement object for sending SQL to the database.
	*It executes the SQL and returns the result it produces*/	
	 	stmt = conn.createStatement();                      
	 /*executeQuery() method executes the SQL statement which returns a single ResultSet type object.*/
	 	rs =  stmt.executeQuery(sqlCommand);
	}
	catch(Exception e){
		System.out.println("Couldn't connect to DB");
	}
		return rs;

	}


}
