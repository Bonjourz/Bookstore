package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.StaticsDao;
import model.Statics;
import service.StaticsService;
public class StaticsServiceImpl implements StaticsService {
	private StaticsDao staticsDao;
	
	public void setStaticsDao(StaticsDao staticsDao) {
		this.staticsDao = staticsDao;
	}
	
	public Map<String, Integer> getTheTotalPriceMonth() {
		List<Statics> statics_list = staticsDao.getAllStatics();
	
		if (statics_list == null) 
			return null;

		Map<String, Integer> result_map = new HashMap<String, Integer>();
		for (Statics ele : statics_list) {
			if (!result_map.containsKey(ele.getMonth())) {
				result_map.put(ele.getMonth(), (int) ele.getPrice());
			}
			
			else
				result_map.put(ele.getMonth(), result_map.get(ele.getMonth()) + (int) ele.getPrice());
		}
		return result_map;
	}

	@Override
	public Map<String, Integer> getTypeData() {
		List<Statics> statics_list = staticsDao.getAllStatics();
		
		if (statics_list == null)
			return null;
		
		Map<String, Integer> result_map = new HashMap<String, Integer>();
		for (Statics ele : statics_list) {
			if (!result_map.containsKey(ele.getType())) {
				result_map.put(ele.getType(), (int) ele.getPrice());
			}
			
			else
				result_map.put(ele.getType(), result_map.get(ele.getType()) + (int) ele.getPrice());
		}
		return result_map;
	}

	@Override
	public Map<Integer, Integer> getBuyerData(int buyerId) {
		List<Statics> statics_list = staticsDao.getBuyerStatics(buyerId);
		
		if (statics_list == null)
			return null;
		
		Map<Integer, Integer> result_map = new HashMap<Integer, Integer>();
		for (Statics ele : statics_list) {
			if (!result_map.containsKey(ele.getType())) {
				result_map.put(ele.getBuyerId(), (int) ele.getPrice());
			}
			
			else
				result_map.put(ele.getBuyerId(), result_map.get(ele.getBuyerId()) + (int) ele.getPrice());
		}
		return result_map;
	}
}
