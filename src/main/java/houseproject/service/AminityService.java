package houseproject.service;

import java.util.List;

import houseproject.model.AminityModel;

public interface AminityService 
{
	public boolean isAddNewAminity(AminityModel model);
	public List<AminityModel> getAllAminities();

}
