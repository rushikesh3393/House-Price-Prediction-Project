package houseproject.clientapp;

import java.util.*;

import houseproject.model.AminityModel;
import houseproject.model.CityModel;
import houseproject.model.DistModel;
import houseproject.model.PropertyModel;
import houseproject.model.StateModel;
import houseproject.model.WardModel;
import houseproject.service.StateService;
import houseproject.service.StateServiceImpl;
import houseproject.service.CityService;
import houseproject.service.CityServiceImpl;
import houseproject.service.AminityService;
import houseproject.service.AminityServiceImpl;
import houseproject.service.PredictionService;
import houseproject.service.PredictionServiceImpl;
import org.apache.log4j.*;
import lombok.*;



public class HousePricePredictApp {

	static int count=0;
	
	static Logger logger=Logger.getLogger(HousePricePredictApp.class);
	static {
		PropertyConfigurator.configure("C:\\Users\\HP\\eclipse-workspace\\HousePricePrediction\\src\\main\\resources\\Application.properties");
	}
	public static void main(String[] args) {
		StateService stateService = new StateServiceImpl();
		CityService cityService=new CityServiceImpl();
		AminityService aminityService=new AminityServiceImpl();
		PredictionService predService=new PredictionServiceImpl();
		logger.debug("Main Method Started and State Service And City Service Created");
		
		
		do {
			Scanner xyz = new Scanner(System.in);
			System.out.println("\n");

			System.out.println("1:Add New State");
			System.out.println("2:View All State");
			System.out.println("3:Enter State Name For Search");
			System.out.println("4:Delete State By Id");
			System.out.println("5:Update State By Name");
			System.out.println("6:Add New Single District in State");
			System.out.println("7:Add BULK  District in State");
			System.out.println("8:Add New City");
			System.out.println("9:Add New Ward IN City");
			System.out.println("10:Add New Amenities");
			System.out.println("11:Add New Property");
			System.out.println("12:Prediction Logics");
			
			
			System.out.println("Enter Your Choice");
			int choice = xyz.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter State Name");
				xyz.nextLine();
				System.out.println(
						(stateService.isAddNewState(new StateModel(0, xyz.nextLine()))) ? "State Added Successfully..!"
								: "State NOT Added Successfully..!");
				break;

			case 2:
				Optional<List<StateModel>> o = stateService.getAllStates();
				if (o.isPresent()) {

					o.get().forEach((state) -> System.out.println(state.getStateId() + "\t" + state.getStateName()));
				}
				else
				{
					System.out.println("States NOT Found");
				}

				break;

			case 3:
				System.out.println("Enter the State Name:");
				xyz.nextLine();
				String stateName = xyz.nextLine();
				StateModel model = stateService.getStateByName(stateName);
				if (model != null) {
					System.out.println(model.getStateId() + "\t" + model.getStateName());
				} else {
					System.out.println("State NOT Found");
				}
				break;

			case 4:
				System.out.println("Enter the State Name:");
				xyz.nextLine();
				stateName = xyz.nextLine();
				boolean b = stateService.isDeleteStateById(stateName);
				if (b) {
					System.out.println("State Deleted SuccessFully...!");
				} else {
					System.out.println("State NOT Deleted SuccessFully...!");
				}
				break;

			case 5:
				xyz.nextLine();
				System.out.println("Enter State Current Name");
				String currName = xyz.nextLine();
				System.out.println("Enter State New Name");
				String newName = xyz.nextLine();
				b = stateService.isUpdateState(currName, newName);
				if (b) {
					System.out.println("State Updated SuccessFully...!");
				} else {
					System.out.println("State NOT Updated SuccessFully...!");
				}

				break;
				
			case 6:
				System.out.println("Enter State Name");
				xyz.nextLine();
			    stateName=xyz.nextLine();
				System.out.println("Enter District Name");
				String distName=xyz.nextLine();
				b=stateService.isAssociateDistToState(stateName, distName);
				if(b)
				{
					System.out.println("District Added Under State");
				}
				else
				{
					System.out.println("District NOT Added Under State");
				}
				break;
			case 7:
				System.out.println("Enter State Name");
				xyz.nextLine();
			    stateName=xyz.nextLine();
			    b=stateService.isAddBulkDist(stateName);
				if(b)
				{
					System.out.println("Districts Added Under State");
				}
				else
				{
					System.out.println("Districts NOT Added Under State");
				}
				break;
				
			case 8:
			     o = stateService.getAllStates();
			     
				if (o.isPresent()) {

					o.get().forEach((state) -> System.out.println( (++count)+ "\t" +state.getStateName()));
					System.out.println("Enter State Name in Which City Want To Add");
					xyz.nextLine();
					stateName=xyz.nextLine();
					System.out.println("District Name");
					System.out.println("-----------------------------------------------");
					List<DistModel> list=stateService.getDistListByName(stateName);
					if(list!=null)
					{
						list.forEach((dist)->System.out.println(dist.getId()+"\t"+dist.getName()));
						list.clear();
						System.out.println("Enter The District Name In Which City Want To Add");
					    distName=xyz.nextLine();
					    int stateId=stateService.getStateIdByName(stateName);
					    int distId=stateService.getDistIdByDistName(distName);
					    System.out.println(stateId+"\t"+distId);
					    System.out.println("Enter the CityName");
					    String cityName=xyz.nextLine();
					    CityModel cityModel=new CityModel();
					    cityModel.setId(distId);
					    cityModel.setStateId(stateId);
					    cityModel.setCityName(cityName);
					    b=cityService.isAddNewCity(cityModel);
					    if(b)
					    {
					    	System.out.println(cityName+" City Added SuccessFully..!");
					    }
					    else
					    {
					    	System.out.println("Some Problem is There...!");
					    }
					        
					}
					else
					{
						System.out.println("Do You Want To Add "+stateName+" In Database");
						String msg=xyz.nextLine();
						if(msg.equals("yes"))
						{
							b=stateService.isAddNewState(new StateModel(0,stateName));
							if(b)
							{
								System.out.println(stateName+" State Added In Database Sucessfully..!");
							}
							else
							{
								System.out.println(stateName+" NOT Added In Database Sucessfully..!");
							}
						}
						else
						{
							System.out.println("No");
						}
					}
					
				}
				else
				{
					System.out.println("There Is NO Data Present In State Table");
				}
				
				break;
				
			case 9:
			
				o = stateService.getAllStates();
			     
				if (o.isPresent()) {

					o.get().forEach((state) -> System.out.println( (++count)+ "\t" +state.getStateName()));
					System.out.println("Enter State Name in Which City Want To Add");
					xyz.nextLine();
					stateName=xyz.nextLine();
					System.out.println("District Name");
					System.out.println("-----------------------------------------------");
					List<DistModel> list=stateService.getDistListByName(stateName);
					if(list!=null)
					{
						list.forEach((dist)->System.out.println(dist.getId()+"\t"+dist.getName()));
						list.clear();
						System.out.println("Enter The District Name In Which City Want To Add");
					    distName=xyz.nextLine();
					    int stateId=stateService.getStateIdByName(stateName);
					    int distId=stateService.getDistIdByDistName(distName);
					    System.out.println(stateId+"\t"+distId);
					    System.out.println("Enter the CityName");
					    String cityName=xyz.nextLine();
					    int cityId=cityService.getCityIdByName(cityName,stateId,distId);
					    System.out.println("Enter the Ward Name And Its Pincode");
					    String wardName=xyz.nextLine();
					    String wardPinCode=xyz.nextLine();
					    WardModel wardModel=new WardModel(wardName,0,wardPinCode);
					    wardModel.setCityId(cityId);
					    b=cityService.isAddNewWard(wardModel);
					    if(b)
					    {
					    	System.out.println(wardName+" is Successfully Added As Ward");
					    }
					    else
					    {
					    	System.out.println("Some Problem Is There....!");
					    }
					         
					}
					else
					{
						System.out.println("Do You Want To Add "+stateName+" In Database");
						String msg=xyz.nextLine();
						if(msg.equals("yes"))
						{
							b=stateService.isAddNewState(new StateModel(0,stateName));
							if(b)
							{
								System.out.println(stateName+" State Added In Database Sucessfully..!");
							}
							else
							{
								System.out.println(stateName+" NOT Added In Database Sucessfully..!");
							}
						}
						else
						{
							System.out.println("No");
						}
					}
					
				}
				else
				{
					System.out.println("There Is NO Data Present In State Table");
				}
				
				break;
				
				
			case 10:
				System.out.println("Add New Ammenities");
				xyz.nextLine();
				String aminityName=xyz.nextLine();
				AminityModel amModel=new AminityModel(0,aminityName);
				b=aminityService.isAddNewAminity(amModel);
				if(b)
				{
					System.out.println("Aminity Added SuccessFully...!");
					logger.info("Aminity Added SuccessFully");
				}
				else
				{
					System.out.println("There Is Some Problem In Aminity Insertion");
					logger.info("There Is Some Problem In Aminity Insertion");
				}
				
				break;
				
				
			case 11:
				o = stateService.getAllStates();
			     
				if (o.isPresent()) {

					o.get().forEach((state) -> System.out.println( (++count)+ "\t" +state.getStateName()));
					System.out.println("Enter State Name in Which City Want To Add");
					xyz.nextLine();
					stateName=xyz.nextLine();
					System.out.println("District Name");
					System.out.println("-----------------------------------------------");
					List<DistModel> list=stateService.getDistListByName(stateName);
					if(list!=null)
					{
						list.forEach((dist)->System.out.println(dist.getId()+"\t"+dist.getName()));
						list.clear();
						System.out.println("Enter The District Name In Which City Want To Add");
					    distName=xyz.nextLine();
					    int stateId=stateService.getStateIdByName(stateName);
					    int distId=stateService.getDistIdByDistName(distName);
					    System.out.println("City List");
					    System.out.println("------------------------------");
					    
					    List<CityModel> cityList=cityService.getAllCities(stateId, distId);
					    cityList.forEach((c)->System.out.println(c.getCityId()+"\t"+c.getCityName()));
					    System.out.println("======================================");
					    System.out.println("Enter the City Name For Fetch Ward");
					    String cityName=xyz.nextLine();
					    List<WardModel> wardList=cityService.getAllWardsByCityName(cityName);
					    System.out.println("Choose Ward name in Which Property Want TO Purchase");
					    System.out.println("=======================================");
					    wardList.forEach((w)->System.out.println(w.getWardName()));
					    System.out.println("======================================");
					    System.out.println("Enter The Ward Name");
					    String wardName=xyz.nextLine();
					    int wardId=cityService.getWardIdByName(wardName);
					    System.out.println("Enter the Property Detail Like Name,Address, Area(Sq.Feet) And price");
					    String propertyName=xyz.nextLine();
					    String propertyAddress=xyz.nextLine();
					    
					    int sqFeetArea=xyz.nextInt();
					    int propPrice=xyz.nextInt();
					    WardModel wardModel=new WardModel();
					    wardModel.setWardId(wardId);
					    wardModel.setWardName(wardName);
					    
					    System.out.println("=======================================");
					    System.out.println("Select Aminities");
					    List<AminityModel> aminityList=aminityService.getAllAminities();
					    aminityList.forEach((am)->System.out.println(am.getId()+"\t"+am.getName()));
					    List<AminityModel> listAminities=new ArrayList<AminityModel> ();
					    xyz.nextLine();
					    while(true) {
					    	System.out.println("select Aminity name id And Price");
					    	
					    	String name=xyz.nextLine();
					    	int amid=xyz.nextInt();
					    	int amPrice=xyz.nextInt();
					    	amModel=new AminityModel();
					    	amModel.setId(amid);
					    	amModel.setName(name);
					    	amModel.setPrice(amPrice);
					    	listAminities.add(amModel);
					    	System.out.println("Do You Want To Add Another Aminities");
					    	xyz.nextLine();
					    	String confirmMsg=xyz.nextLine();
					    	if(confirmMsg.equals("no"))
					    	{
					    		break;
					    	}
					    }
					   
					    PropertyModel propModel=new PropertyModel(wardModel,0,propertyName,propertyAddress,propPrice,sqFeetArea,listAminities);
					    b=cityService.isAddNewProperty(propModel);
					    if(b)
					    {
					    	System.out.println("Property Added SuccessFully...!");
					    }
					    else
					    {
					    	System.out.println("Property NOT Added SuccessFully...!");	
					    }
					}
					else
					{
						System.out.println("Do You Want To Add "+stateName+" In Database");
						String msg=xyz.nextLine();
						if(msg.equals("yes"))
						{
							b=stateService.isAddNewState(new StateModel(0,stateName));
							if(b)
							{
								System.out.println(stateName+" State Added In Database Sucessfully..!");
							}
							else
							{
								System.out.println(stateName+" NOT Added In Database Sucessfully..!");
							}
						}
						else
						{
							System.out.println("No");
						}
					}
					
				}
				else
				{
					System.out.println("There Is NO Data Present In State Table");
				}
				
				
				break;
				
			case 12:
				o = stateService.getAllStates();
			     
				if (o.isPresent()) {

					o.get().forEach((state) -> System.out.println( (++count)+ "\t" +state.getStateName()));
					System.out.println("Enter State Name in Which City Want To Add");
					xyz.nextLine();
					stateName=xyz.nextLine();
					System.out.println("District Name");
					System.out.println("-----------------------------------------------");
					List<DistModel> list=stateService.getDistListByName(stateName);
					if(list!=null)
					{
						list.forEach((dist)->System.out.println(dist.getId()+"\t"+dist.getName()));
						list.clear();
						System.out.println("Enter The District Name In Which City Want To Add");
					    distName=xyz.nextLine();
					    int stateId=stateService.getStateIdByName(stateName);
					    int distId=stateService.getDistIdByDistName(distName);
					    System.out.println("City List");
					    System.out.println("------------------------------");
					    
					    List<CityModel> cityList=cityService.getAllCities(stateId, distId);
					    cityList.forEach((c)->System.out.println(c.getCityId()+"\t"+c.getCityName()));
					    System.out.println("======================================");
					    System.out.println("Enter the City Name For Fetch Ward");
					    String cityName=xyz.nextLine();
					    List<WardModel> wardList=cityService.getAllWardsByCityName(cityName);
					    System.out.println("Choose Ward name in Which Property Want TO Purchase");
					    System.out.println("=======================================");
					    wardList.forEach((w)->System.out.println(w.getWardName()));
					    System.out.println("======================================");
					    System.out.println("Enter The Ward Name");
					    String wardName=xyz.nextLine();
					    int wardId=cityService.getWardIdByName(wardName);
					    int minX=predService.getMinX(wardId);
					    System.out.println("Min of X is:"+minX);
					    int minY=predService.getMinY(wardId);
					    System.out.println("Min of Y is:"+minY);
					  
					   
					   int m=(predService.getProdDevXY(wardId)/predService.getSquareDevX(wardId));
					   System.out.println("Value of m is:"+m);
					   int intercept=minY-(m*minX);
					   System.out.println("Intercepted value is "+intercept);
					   System.out.println("Enter The Area In SquareFeet");
					   int squareFeet=xyz.nextInt();
					   int predPrice=(m*squareFeet)+intercept;
					   System.out.println("Your Predicted Price of House in "+wardName+"\t"+predPrice);
					   }
					else
					{
						System.out.println("Do You Want To Add "+stateName+" In Database");
						String msg=xyz.nextLine();
						if(msg.equals("yes"))
						{
							b=stateService.isAddNewState(new StateModel(0,stateName));
							if(b)
							{
								System.out.println(stateName+" State Added In Database Sucessfully..!");
							}
							else
							{
								System.out.println(stateName+" NOT Added In Database Sucessfully..!");
							}
						}
						else
						{
							System.out.println("No");
						}
					}
					
				}
				else
				{
					System.out.println("There Is NO Data Present In State Table");
				}
				
				
				break;
				
			case 13:
				System.exit(0);

			default:
				System.out.println("Wrong Choice");
			}
		} while (true);
	}

}
