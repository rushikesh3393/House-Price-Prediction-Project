package houseproject.repository;

import houseproject.model.CityModel;
import houseproject.model.PropertyModel;
import houseproject.model.WardModel;
import java.util.*;

public interface CityRepository 
{
	public boolean isAddNewCity(CityModel model);
	public int getCityIdByCityName(String cityName,int stateId,int distId) ;
	public boolean isAddNewWard(WardModel wardModel);
	public List<CityModel> getAllCities(int stateid,int distid);
	public List<WardModel> getAllWardsByCityName(String cityName);
	public int getWardIdByName(String wardName);
	public boolean isAddNewProperty(PropertyModel model);
	

}
