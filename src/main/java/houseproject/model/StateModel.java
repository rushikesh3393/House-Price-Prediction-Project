package houseproject.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class StateModel 
{
	private int stateId;
	private String stateName;
	
	/*public StateModel()
	{
		
	}
	public StateModel(int id,String Name)
	{
		this.stateId=id;
		this.stateName=Name;
		
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String toString()
	{
		return "["+stateId+","+stateName+"]";
	}
	public boolean equals(Object obj)
	{
		StateModel model=(StateModel)obj;
		if(model.getStateId()==this.stateId && model.getStateName().equals(this.stateName))
		{
		return true;
		}
		else
		{
			return false;
		}
	}
	public int hashcode()
	{
		return stateId*10000;
		
		
	}*/

}
