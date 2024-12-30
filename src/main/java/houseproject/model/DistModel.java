package houseproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter


public class DistModel extends StateModel
{
	
	private String name;
	private int id;
	
    public  DistModel()
	{
		
	}
	public DistModel(String name,int id)
	{
		this.name=name;
		this.id=id;
	}
	
	/*public int getId() 
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}*/

}
