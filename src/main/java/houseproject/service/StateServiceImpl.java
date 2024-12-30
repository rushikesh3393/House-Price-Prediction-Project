package houseproject.service;

import java.util.List;
import java.util.Optional;

import houseproject.model.DistModel;
import houseproject.model.StateModel;
import houseproject.repository.StateRepository;
import houseproject.repository.StateRepositoryImpl;

public class StateServiceImpl implements StateService {

	StateRepository stmtRepo=new StateRepositoryImpl();
	
	public boolean isAddNewState(StateModel model) 
	{
		
		return stmtRepo.isAddNewState(model);
	}

	
	public Optional<List<StateModel>> getAllStates() {
	
		return stmtRepo.getAllStates();
	}


	public StateModel getStateByName(String stateName) {
		
		return stmtRepo.getStateByName(stateName);
	}

	public boolean isDeleteStateById(String stateName) {
		
		return stmtRepo.isDeleteState(stateName);
	}


	public boolean isUpdateState(String currName, String newName) 
	{
		
		return stmtRepo.isUpdateState(currName, newName);
	}


	public boolean isAssociateDistToState(String stateName, String distName) {
		
		return stmtRepo.isAssociateDistToState(stateName, distName);
	}


	public boolean isAddBulkDist(String stateName) {
		return stmtRepo.isAddBulkDist(stateName);
	}

	
	public List<DistModel> getDistListByName(String stateName) 
	{
		return stmtRepo.getDistListByName(stateName);
	}


	public int getStateIdByName(String stateName) {
		return stmtRepo.getStateIdByName(stateName);
	}


	@Override
	public int getDistIdByDistName(String distName) 
	{
		return stmtRepo.getDistIdByDistName(distName);
	}

}
