package houseproject.model;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyModel {
	private WardModel model=new WardModel();
	private int propId;
	private String propName;
	private String propAddress;
	private int propPrice;
	private int sqfeetArea;
	
	private List<AminityModel> list;

}
