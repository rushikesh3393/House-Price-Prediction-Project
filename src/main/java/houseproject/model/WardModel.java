package houseproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


public class WardModel extends CityModel {
	
	private String wardName;
	private String wardPinCode;
	private int wardId;

	public WardModel()
	{
		
	}
	
	public WardModel(String wardName,int warId,String wardPinCode)
	{
		this.wardName=wardName;
		this.wardPinCode=wardPinCode;
		this.wardId=wardId;
	}
	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getWardPinCode() {
		return wardPinCode;
	}

	public void setWardPinCode(String wardPinCode) {
		this.wardPinCode = wardPinCode;
	}

}
