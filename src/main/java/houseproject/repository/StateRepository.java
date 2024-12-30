package houseproject.repository;

import houseproject.model.DistModel;
import houseproject.model.StateModel;
import java.util.*;


public interface StateRepository 
{
	public boolean isAddNewState(StateModel model);
	public Optional<List<StateModel>> getAllStates();
	public StateModel getStateByName(String stateName);
	public boolean isDeleteState(String stateName);
	public int getStateIdByName(String stateName);
	public boolean isUpdateState(String currName,String newName);
	public boolean isAssociateDistToState(String stateName,String distName);
	public boolean isAddBulkDist(String stateName);
	public List<DistModel> getDistListByName(String stateName);
	public int getDistIdByDistName(String distName);

}
