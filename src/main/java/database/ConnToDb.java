package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import logger.Log;

public class ConnToDb 
{

	protected static Connection conn = null;

	static Config c =new Config();
	private static String connessione="Tentativo di conessione al server..........\\\\n";
	protected static String url2;
	

	public static  boolean initailConnection()
	{

		try
		{
			Class.forName(c.getDriver());
			Log.logger.log(Level.INFO ,connessione);

			conn = DriverManager.getConnection(c.getUrl(), c.getUser(),c.getPwd());
			Log.logger.log(Level.INFO,"Connesso initial..........\\n");

			return true;

		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
			Log.logger.log(Level.SEVERE,"Errore in mysql..........\\n");

		} 
		catch (ClassNotFoundException e2)
		{
			e2.printStackTrace();
			Log.logger.log(Level.SEVERE,"Errore in mysql..........\\n");

		}
		return false;
	}

	public static boolean connection() throws SQLException {

		try 
		{
			if(initailConnection()) 
			{
				//actuac DB project

				 url2 = "jdbc:mysql://localhost/finder?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				Class.forName(c.getDriver());
				
				Log.logger.log(Level.INFO,"Tentativo di conessione al server..........\\n");

				conn = DriverManager.getConnection(url2, c.getUser(),c.getPwd());
				Log.logger.log(Level.INFO,"Connesso standard..........\\n");

				return true;
			}
			else
				return false;
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
			Log.logger.log(Level.SEVERE,"Errore mysql..........\\n");

		} 
		catch (ClassNotFoundException e2) 
		{
			e2.printStackTrace();
			Log.logger.log(Level.SEVERE,"Errore mysql..........\\n");

		}			
		

		return false;
	}
	
	public static Connection generalConnection()
	{

		try
		{
			 url2 = "jdbc:mysql://localhost/finder?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Class.forName(c.getDriver());
			Log.logger.log(Level.INFO,"Tentativo di conessione al server..........\\n");
			conn = DriverManager.getConnection(url2, c.getUser(),c.getPwd());
			Log.logger.log(Level.INFO,"Connesso standard..........\\n");

			return conn;
		} 
		catch (SQLException e1)
		{
			e1.printStackTrace();
			Log.logger.log(Level.SEVERE,"Errore di sql..........\\n");

		} 
		catch (ClassNotFoundException e2)
		{
			e2.printStackTrace();
			Log.logger.log(Level.SEVERE,"Errore in mysqlr..........\\n");
		}
		return null;			
			
	}
	private ConnToDb(){
		
	}


}

