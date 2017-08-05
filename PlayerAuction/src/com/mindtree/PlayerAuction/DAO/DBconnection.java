/**
 * 
 */
package com.mindtree.PlayerAuction.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author M1041940
 *
 */
public class DBconnection 
{

	/**
	 * @param args
	 * @return 
	 */
	
	public Connection db()throws SQLException {
		// TODO Auto-generated method stub
		Connection myConn=null;
		FileInputStream fs=null;
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			Properties props=new Properties();
			fs=new FileInputStream("database.properties");
			
			props.load(fs);
			
			String theUser=props.getProperty("jdbc.USERNAME");
			String thePassword=props.getProperty("jdbc.PASSWORD");
			String theDburl=props.getProperty("jdbc.URL");
			
		//	System.out.println("Connecting to database.....");
			//System.out.println("Database URL: "+theDburl);
			//System.out.println("user: ",+theUser);
			myConn=DriverManager.getConnection(theDburl,theUser,thePassword);
			System.out.println("\nConnection Successful!\n");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return myConn;
	}
}
