package houseproject.service;

import java.util.List;

import houseproject.model.CityModel;
import houseproject.model.PropertyModel;
import houseproject.model.WardModel;
import houseproject.repository.CityRepository;
import houseproject.repository.CityRepositoryImpl;


public class CityServiceImpl implements CityService
{
	CityRepository cityRepo=new CityRepositoryImpl();

	public boolean isAddNewCity(CityModel model) 
	{
		return cityRepo.isAddNewCity(model) ;
	}

	public int getCityIdByName(String cityName,int stateId,int distId) {
	
		return cityRepo.getCityIdByCityName(cityName,stateId,distId);
	}

	
	public boolean isAddNewWard(WardModel wardModel) {
		
		return cityRepo.isAddNewWard(wardModel);
	}


	public List<CityModel> getAllCities(int stateid, int distid) {
		return cityRepo.getAllCities(stateid, distid);
		
	}

	public List<WardModel> getAllWardsByCityName(String cityName) {
		return cityRepo.getAllWardsByCityName(cityName);
	}

	
	public int getWardIdByName(String wardName) {
		return cityRepo.getWardIdByName(wardName);
	}

	
	public boolean isAddNewProperty(PropertyModel model) 
	{
		return cityRepo.isAddNewProperty(model);
	}

	

}
