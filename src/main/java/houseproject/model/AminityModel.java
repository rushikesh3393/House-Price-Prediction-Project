package houseproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class AminityModel 
{
	private int id;
	private String name;
	private int price;
	
	public AminityModel()
	{
		
	}
	public AminityModel(int id,String name)
	{
		this.id=id;
		this.name=name;
		this.price=price;
		
	}

}
