package service;

import java.util.Map;

public interface StaticsService {
	public Map<String, Integer> getTheTotalPriceMonth();
	
	public Map<String, Integer> getTypeData();
	
	public Map<Integer, Integer> getBuyerData(int buyerId);
}
