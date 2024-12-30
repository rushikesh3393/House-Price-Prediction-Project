package houseproject.repository;
import java.sql.*;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import java.io.*;

public class DBConfig 
{
	protected static Connection conn;
	protected static PreparedStatement stmt;
	protected static ResultSet rs;
	protected static CallableStatement cstmt;
	private static DBConfig db=null;
	private static Logger logger=Logger.getLogger(DBConfig.class);
	static {
		SimpleLayout layout=new SimpleLayout();
		ConsoleAppender appender=new ConsoleAppender(layout);
		logger.addAppender(appender);
		logger.setLevel(Level.DEBUG);
	}
	
	protected DBConfig()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			File f=new File("");
			String path=f.getAbsolutePath();
			
			FileInputStream inputStream=new FileInputStream(path+"\\src\\main\\resources\\dbconfig.properties");
			Properties p=new Properties();
			p.load(inputStream);
			String driverClassName=p.getProperty("driver");
			String username=p.getProperty("username");
			String password=p.getProperty("password");
			String url=p.getProperty("url");
			Class.forName(driverClassName);
             conn=DriverManager.getConnection(url, username, password);
             if(conn!=null)
             {
            	 logger.info("DataBase Is Connected");
             }
             else
             {
            	 logger.info("DataBase Connection Is Failed");
             }
		
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
		}
	}
	
	public static DBConfig getInstance()
	{
		if(db==null)
		{
			db=new DBConfig();
		}
		return db;
	}
	public static Connection getConn()
	{
		return conn;
	}
	public static PreparedStatement getStatement()
	{
		return stmt;
	}
	public static ResultSet getResult()
	{
		return rs;
	}
	public static CallableStatement getCallStatement()
	{
		return cstmt;
	}

}
