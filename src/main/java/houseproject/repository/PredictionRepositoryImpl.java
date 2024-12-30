package houseproject.repository;

import java.util.ArrayList;
import java.util.List;

public class PredictionRepositoryImpl extends DBSTATE implements PredictionRepository 
{
	List<Integer> list;

	public int getMinX(int wardId) 
	{
		try {
			stmt=conn.prepareStatement("select avg(sqfeet) from property where wardid=?");
			stmt.setInt(1, wardId);
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
			System.out.println("Error is:"+ex);
			return -1;
		}
		
	
	}

	
	public int getMinY(int wardId) 
	{
		try 
		{
		stmt=conn.prepareStatement("select avg(price) from property where wardid=?");
		stmt.setInt(1, wardId);
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
		System.out.println("Error is:"+ex);
		return -1;
	}
	}


	public List<Integer> getDevX(int wardId) 
	{
		try 
		{
			list=new ArrayList<Integer>();
			stmt=conn.prepareStatement("select sqfeet from property");
			rs=stmt.executeQuery();
			while(rs.next())
			{
				list.add(rs.getInt(1));
			}
			return list;
			
		}
		catch(Exception ex)
		{
			return null;
			
		}
	
		
	}



	public List<Integer> getDevY(int wardId) 
	{
		try 
	    {
		list=new ArrayList<Integer>();
		stmt=conn.prepareStatement("select price from property");
		rs=stmt.executeQuery();
		while(rs.next())
		{
			list.add(rs.getInt(1));
		}
		return list;
		
	}
	catch(Exception ex)
	{
		return null;
		
	}
	}

}
