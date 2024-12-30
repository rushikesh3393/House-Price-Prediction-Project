package houseproject.repository;

import java.util.List;
import org.apache.log4j.Logger;
import java.util.*;
import houseproject.model.CityModel;
import houseproject.model.WardModel;
import houseproject.model.DistModel;
import houseproject.model.PropertyModel;
import houseproject.model.AminityModel;

public class CityRepositoryImpl extends DBSTATE implements CityRepository
{
	boolean flag=false;
	Logger logger=Logger.getLogger(CityRepositoryImpl.class);
	List<CityModel> cityList;
	List<WardModel> wardList;
	
	public boolean isAddNewCity(CityModel model) 
	{
		try
		{
			cstmt=conn.prepareCall("{call saveCity(?,?,?)}");
			cstmt.setString(1,model.getCityName());
			cstmt.setInt(2, model.getStateId());
			cstmt.setInt(3,model.getId());
			
			boolean b=cstmt.execute();
			return !b;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			return false;
		}
		
	}

	public int getCityIdByCityName(String cityName,int stateId,int distId) 
	{
		try 
		{
			stmt=conn.prepareStatement("select cm.cityid from citymaster cm inner join citydistjoin cdj on cdj.cityid=cm.cityid where cm.cityname=? and cdj.stateid=? and cdj.distid=?");
		    stmt.setString(1,cityName);
		    stmt.setInt(2, stateId);
		    stmt.setInt(3,distId);
		    rs=stmt.executeQuery();
		    if(rs.next()) {
		    	return rs.getInt(1);
		    }
		    else
		    {
		    	return 0;
		    }
		    
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			return 0;
		}
	}

	public boolean isAddNewWard(WardModel wardModel) 
	{
		try 
		{
			cstmt=conn.prepareCall("{ call saveWard(?,?,?)}");
			cstmt.setString(1, wardModel.getWardName());
			cstmt.setString(2, wardModel.getWardPinCode());
			cstmt.setInt(3, wardModel.getCityId());
			boolean b=cstmt.execute();
			return !b;
		}
		catch(Exception ex)
		{
			System.out.println("Error is:"+ex);
			return false;
		}
	}


	public List<CityModel> getAllCities(int stateid,int distid) {
		cityList=new ArrayList<CityModel> ();
		try{
			stmt=conn.prepareStatement("select c.cityname,c.cityid from citymaster c inner join citydistjoin cdj on c.cityid=cdj.cityid inner join distmaster d on d.distid=cdj.distid where cdj.stateid=? and d.distid=?");
		    stmt.setInt(1, stateid);
		    stmt.setInt(2, distid);
		    rs=stmt.executeQuery();
		    
		    while(rs.next())
		    {
		    	//CityModel cmodel=new CityModel(rs.getInt(2),rs.getString(1));
		    	cityList.add(new CityModel(rs.getInt(2),rs.getString(1)));
		    }
		    return cityList.size()>0?cityList:null;
		}
		catch(Exception ex)
		{
			
		}
		return null;
	}

	public List<WardModel> getAllWardsByCityName(String cityName) {
	
		try {
			wardList=new ArrayList<WardModel> ();
			stmt=conn.prepareStatement("select wm.wardname from wardmaster wm inner join citywardjoin cwj on wm.wardid=cwj.wardid inner join citymaster cm on cm.cityid=cwj.cityid where cm.cityname=?");
			stmt.setString(1, cityName);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				wardList.add(new WardModel(rs.getString(1),0,null));
			}
			return wardList.size()>0?wardList:null;
			
		}
		catch(Exception ex)
		{
			logger.fatal("CityRepositoryImpl:: getAllWardByCityNAme Error is:"+ex.getMessage());
			return null;
		}
	}

	public int getWardIdByName(String wardName) {
        try 
        {
        	stmt=conn.prepareStatement("select wardid from wardmaster where wardname=?");
        	stmt.setString(1, wardName);
        	rs=stmt.executeQuery();
        	if(rs.next())
        	{
        		return rs.getInt(1);
        	}
        	else
        	{
        		return 0;
        	}
        }
        catch(Exception ex)
        {
        	logger.fatal("CityRepositoryImpl:: getWardIdByName Error is:"+ex.getMessage());
        	return -1;
        }
	}

	public int getPropIdByName(String propName)
	{
		int propId=0;
		try {
			stmt=conn.prepareStatement("select pid from property where pname=?");
			stmt.setString(1, propName);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
			}
			else
			{
				return -1;
			}
		}
		catch(Exception ex)
		{
			logger.fatal("CityRepository::getPropIdByname Error is:"+ex.getMessage());
			return -1;
		}
	}

	public boolean isAddNewProperty(PropertyModel model) 
	{
		WardModel wardModel=model.getModel();
		int wardId=wardModel.getWardId();
		int propId=model.getPropId();
		String propName=model.getPropName();
		String propAddress=model.getPropAddress();
		int sqFeetArea=model.getSqfeetArea();
		int propPrice=model.getPropPrice();
        try {
        	stmt=conn.prepareStatement("insert into property values('0',?,?,?,?,?)");
        	stmt.setString(1, propName);
        	stmt.setString(2, propAddress);
        	stmt.setInt(3, propPrice);
        	stmt.setInt(4, wardId);
        	stmt.setInt(5, sqFeetArea);
        	
        	int value=stmt.executeUpdate();
        	
        	if(value>0)
        	{
        		List<AminityModel> aminityList=model.getList();
        		int pid=this.getPropIdByName(propName);
        		
        		if(pid!=-1)
        		{
        	
        		for(AminityModel amModel:aminityList)
        		{
        			
        			stmt=conn.prepareStatement("insert into aminitypropjoin values(?,?,?)");
        			System.out.println(amModel.getId()+"\t"+pid+"\t"+amModel.getPrice());
        			stmt.setInt(1,amModel.getId());
        			stmt.setInt(2,pid);
        			stmt.setInt(3,amModel.getPrice());
        			value=stmt.executeUpdate();
        			if(value>0)
        			{
        				flag=true;
        			}
        			
        			propPrice=propPrice + amModel.getPrice();
        		}
        		
        		stmt=conn.prepareStatement("update property set price=? where pid=?");
        		stmt.setInt(1, propPrice);
        		stmt.setInt(2, pid);
        		 value=stmt.executeUpdate();
        		 if(value>0)
        		 {
        			 flag=true;
        		 }
        		 
        		}
        	}
        	
        	else
        	{
        		System.out.println("Failed to insert property");
        		return false;
        		
            }
        	
        	
		System.out.println("\n total property Price is:"+propPrice);
        }
        catch(Exception ex)
        {
        	logger.fatal("CityRepository:: isAddNewProperty Error is:"+ex.getMessage());
        	return false;
        }
        return flag;
	}
	

}
