package houseproject.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import houseproject.model.AminityModel;

public class AminityRepositoryImpl extends DBSTATE implements AminityRepository
{
    Logger logger=Logger.getLogger(AminityRepositoryImpl.class);
    List<AminityModel> list;
	public boolean isAddNewAminity(AminityModel model) 
	{
		try {
			stmt=conn.prepareStatement("insert into aminities values('0',?)");
			stmt.setString(1, model.getName());
			int value=stmt.executeUpdate();
			return value>0?true:false;
		}
		catch(Exception ex)
		{
			logger.fatal("AminityRepositoryImpl:: Error is:"+ex);
			return false;
		}
		
		
	}
	
	public List<AminityModel> getAllAminities()
	{
		try 
		{
			list=new ArrayList<AminityModel>();
			stmt=conn.prepareStatement("select *from aminities order by amid");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				AminityModel model=new AminityModel();
				model.setId(rs.getInt(1));
				model.setName(rs.getString(2));
				list.add(model);
			}
			return list.size()>0?list:null;
			
		}
		catch(Exception ex)
		{
			logger.fatal("AminityRepository:: getAllAminities"+ex.getMessage());
			return null;
		}
		
		
	}

}
