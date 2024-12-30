package houseproject.repository;

import java.util.*;

import houseproject.model.AminityModel;

public interface AminityRepository 
{
	public boolean isAddNewAminity(AminityModel model);
	public List<AminityModel> getAllAminities();

}
