package houseproject.repository;
import java.sql.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DBSTATE 
{
	DBConfig config=DBConfig.getInstance();
	protected Connection conn=config.getConn();
	protected PreparedStatement stmt=config.getStatement();
	protected ResultSet rs=config.getResult();
	protected CallableStatement cstmt=config.getCallStatement();
	
	static
	{
		PropertyConfigurator.configure("C:\\Users\\HP\\eclipse-workspace\\HousePricePrediction\\src\\main\\resources\\Application.properties");
	}
	

}
