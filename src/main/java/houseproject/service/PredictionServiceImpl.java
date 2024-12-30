package houseproject.service;
import java.util.ArrayList;
import java.util.List;

import houseproject.repository.PredictionRepository;
import houseproject.repository.PredictionRepositoryImpl;


public class PredictionServiceImpl implements PredictionService {

	PredictionRepository predRepo=new PredictionRepositoryImpl();
	List<Integer> listDevX;
	List<Integer> listDevY;
	public int getMinX(int wardId) {
		
		return predRepo.getMinX(wardId);
	}

	
	public int getMinY(int wardId) {
		return predRepo.getMinY(wardId);
	}


	public List<Integer> getDevX(int wardId) {
		
		return predRepo.getDevX(wardId);
	}


	public List<Integer> getDevY(int wardId) {
		
		return predRepo.getDevY(wardId);
	}


	public List<Integer> calDevX(int wardId) {
	
		listDevX=this.getDevX(wardId);
	    System.out.println("Deviation of X");
	    System.out.println("=============================");
	    int count=0;
	    int minX=this.getMinX(wardId);
	    for(Integer val:listDevX)
	    {
	    	int result=val-minX;
	    	listDevX.set(count, result);
	    	++count;
	    	
	    }
		return listDevX;
	}


	public List<Integer> calDevY(int wardId) {
		System.out.println("Deviation of Y");
	    listDevY=this.getDevY(wardId);
	    System.out.println("=============================");
	    int count=0;
	    int minY=this.getMinY(wardId);
	    for(Integer val:listDevY)
	    {
	    	int result=val-minY;
	    	listDevY.set(count, result);
	    	++count;
	    	
	    }
		return listDevY;
	}


	public List<Integer> getDevXY(int wardId) {
		List<Integer> devXYProd=new ArrayList<Integer>();
		listDevX=this.calDevX(wardId);
		listDevY=this.calDevY(wardId);
	    for(int i=0;i<listDevX.size();i++)
	    {
	       Integer valX=listDevX.get(i); 
	       Integer valY=listDevY.get(i);
	       Integer prod=valX*valY;
	       devXYProd.add(prod);
	    }
		return devXYProd;
	}



	public Integer getProdDevXY(int wardId) {
		System.out.println("==============================");
		   int sumProdDev=0;
		   for(Integer val:this.getDevXY(wardId))
		   {
			   sumProdDev=sumProdDev+val;
		   }
		return sumProdDev;
	}


	public Integer getSquareDevX(int wardId) {
		   List<Integer> squareDevX=new ArrayList<Integer>();
		   int sumSquareDevX=0;
		   for(Integer val:this.calDevX(wardId))
		   {
			   //squareDevX.add(val*val);
			   sumSquareDevX=sumSquareDevX+(val*val);
		   }
		return sumSquareDevX;
	}

}
