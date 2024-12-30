package houseproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CityModel extends DistModel {
	private int cityid;
	private String cityname;

	public CityModel()
	{
		
	}
	public CityModel(int cityid,String cityname)
	{
		this.cityid=cityid;
		this.cityname=cityname;
	}
	
	public void setCityId(int id) 
	{
		this.cityid = id;
	}	
	public int getCityId() {
		return cityid;
	}

	public String getCityName() 
	{
		return cityname;
	}

	public void setCityName(String name) {
		this.cityname = name;
	}

	
}
