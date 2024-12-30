package houseproject.service;

import java.util.List;

import houseproject.model.AminityModel;
import houseproject.repository.AminityRepository;
import houseproject.repository.AminityRepositoryImpl;

public class AminityServiceImpl implements AminityService
{

	AminityRepository aminityRepo=new AminityRepositoryImpl();
	
	public boolean isAddNewAminity(AminityModel model) 
	{
		return aminityRepo.isAddNewAminity(model);
	}

	public List<AminityModel> getAllAminities() {
		return aminityRepo.getAllAminities();
	}

}
