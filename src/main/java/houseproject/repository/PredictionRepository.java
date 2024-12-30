package houseproject.repository;

import java.util.*;

public interface PredictionRepository
{
	public int getMinX(int wardId);
	public int getMinY(int wardId);
	public List<Integer> getDevX(int wardId);
	public List<Integer> getDevY(int wardId);
}
