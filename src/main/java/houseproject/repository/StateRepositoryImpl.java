package houseproject.repository;

import java.sql.PreparedStatement;
import java.util.*;
import org.apache.log4j.*;
import java.sql.*;
import houseproject.custexe.StateException;
import houseproject.model.DistModel;
import houseproject.model.StateModel;
import java.io.*;

public class StateRepositoryImpl extends DBSTATE implements StateRepository {
	List<StateModel> list;
	List<DistModel> distList=new ArrayList<DistModel>();
	static Logger logger=Logger.getLogger(StateRepositoryImpl.class);
	
	public boolean isAddNewState(StateModel model) {
		try {
		
			stmt = conn.prepareStatement("insert into statemaster values('0',?)");
			stmt.setString(1, model.getStateName());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;

		} catch (Exception ex) {
			
			logger.fatal("Error in State Insertion:"+ex);
			return false;
		}

	}

	public Optional<List<StateModel>> getAllStates() {
		try {
			list= new ArrayList<StateModel>();
			stmt = conn.prepareStatement("select *from statemaster");
			rs = stmt.executeQuery();
			while (rs.next()) {

				list.add(new StateModel(rs.getInt(1), rs.getString(2)));
			}
			return list.size() > 0 ? Optional.ofNullable(list) : null;

		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
		}
		return null;
	}

	public StateModel getStateByName(String stateName) {
		try {
			stmt = conn.prepareStatement(Query.getStateByName);
			stmt.setString(1, stateName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new StateModel(rs.getInt(1), rs.getString(2));
			} else {
				throw new StateException("State NOT Found Exception: " + stateName);
			}

		} catch (SQLException ex) {
			System.out.println("Error is:" + ex);
			return null;
		} catch (StateException ex) {
			System.out.println("Error is:" + ex.getErrorMsg());
			return null;
		}
	}

	public boolean isDeleteState(String stateName) {
		try {
			int stateId = this.getStateIdByName(stateName);
			if (stateId != -1) {
				stmt = conn.prepareStatement("delete from statemaster where stateid=?");
				stmt.setInt(1, stateId);
				int value = stmt.executeUpdate();
				return value > 0 ? true : false;
			} else {
				return false;
			}

		} catch (Exception ex) {
			//System.out.println("Error is:" + ex);
			logger.fatal("Error is in StateRepositoryImpl:: isDeleteState:"+ex);
			
			return false;
		}
	}

	public int getStateIdByName(String stateName) {
		try {
			stmt = conn.prepareStatement("select stateid from statemaster where statename=?");
			stmt.setString(1, stateName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);

			} else {
				return -1;
			}
		} catch (Exception ex) {
			//System.out.println("Error is:" + ex);
			logger.fatal("Error is in StateRepositoryImpl:: getStateIdByName:"+ex);
			return -1;

		}

	}

	public boolean isUpdateState(String currName, String newName) {
		try {
			int stateId = this.getStateIdByName(currName);
			if (stateId != -1) {
				stmt = conn.prepareStatement("Update statemaster set statename=? where stateid=?");
				stmt.setString(1, newName);
				stmt.setInt(2, stateId);
				int value = stmt.executeUpdate();
				return value > 0 ? true : false;
			}

			else {
				return false;
			}
		} catch (Exception ex) 
		{
			logger.fatal("Error is in StateRepositoryImpl:: isUpdateState:"+ex);
			return false;
		}
	}

	public boolean isAssociateDistToState(String stateName, String distName) {
		try {
			cstmt = conn.prepareCall("{ call saveDist(?,?)}");
			cstmt.setString(1, stateName);
			cstmt.setString(2, distName);
			boolean b = cstmt.execute();
			return !b;
		} catch (Exception ex) {
			//System.out.println("Error is:" + ex);
			logger.fatal("Error is in StateRepositoryImpl:: isAssociateDistToState:"+ex);
			return false;

		}
	}

	public boolean isAddBulkDist(String stateName) {
		try {
			boolean b = false;
			FileReader fr = new FileReader("D:\\District\\MaharashtraDist.txt");
			BufferedReader br = new BufferedReader(fr);

			String distName;
			while ((distName = br.readLine()) != null) {
				cstmt = conn.prepareCall("{ call saveDist(?,?)}");
				cstmt.setString(1, stateName);
				cstmt.setString(2, distName);
				b = cstmt.execute();

			}
			return !b;
		} catch (Exception ex) {
			//System.out.println("Exception is:" + ex);
			logger.fatal("Error is in StateRepositoryImpl:: isAddBulkDist:"+ex);
			return false;
		}
	}

	
	public List<DistModel> getDistListByName(String stateName) 
	{
		try
		{
			stmt=conn.prepareStatement(" select d.distname,d.distid from distmaster d inner join statedistjoin stj on d.distid=stj.distid inner join statemaster  sm on sm.stateid=stj.stateid where sm.statename=?");
			stmt.setString(1, stateName);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				DistModel model=new DistModel(rs.getString(1),rs.getInt(2));
				this.distList.add(model);
				
				
			}
			if(this.distList.size()>0)
			{
				return distList;
			}
			else
			{
				throw new StateException("State NOT Found Exception"+stateName);
			}
		}
		catch(SQLException ex)
		{
			return null;
		}
		catch (StateException ex) {
			System.out.println("Error is:" + ex.getErrorMsg());
			return null;
	}
	}

	public int getDistIdByDistName(String distName) 
	{
		try {
			stmt=conn.prepareStatement("select distid from distmaster where distname=?");
			stmt.setString(1, distName);
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
			return 0;
			
		}
		
	}

}
