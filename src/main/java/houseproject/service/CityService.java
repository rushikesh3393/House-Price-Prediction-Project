package houseproject.service;

import java.util.List;

import houseproject.model.CityModel;
import houseproject.model.PropertyModel;
import houseproject.model.WardModel;

public interface CityService 
{
	public boolean isAddNewCity(CityModel model);
	public int getCityIdByName(String cityName,int stateId,int distId);
	public boolean isAddNewWard(WardModel wardModel);
	public List<CityModel> getAllCities(int stateid,int distid);
	public List<WardModel> getAllWardsByCityName(String cityName);
	public int getWardIdByName(String wardName);
	public boolean isAddNewProperty(PropertyModel model);

}
