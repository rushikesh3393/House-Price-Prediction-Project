package houseproject.service;

import java.util.List;

public interface PredictionService 
{
	public int getMinX(int wardId);
	public int getMinY(int wardId);
	public List<Integer> getDevX(int wardId);
	public List<Integer> getDevY(int wardId);
	public List<Integer> calDevX(int wardId);
	public List<Integer> calDevY(int wardId);
	public List<Integer> getDevXY(int wardId);
	public Integer getProdDevXY(int wardId);
	public Integer getSquareDevX(int wardId);
}
