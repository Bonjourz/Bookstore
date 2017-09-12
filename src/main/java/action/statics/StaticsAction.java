package action.statics;

import java.util.Map;

import action.BaseAction;
import net.sf.json.JSONObject;
import service.StaticsService;

public class StaticsAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private StaticsService staticsService;
	private String result;
	private int buyerId;

	public void setStaticsService(StaticsService staticsService) {this.staticsService = staticsService;}
	
	public String getResult() {return result;}

	public void setResult(String result) {this.result = result;}
	
	public int getBuyerId() {return buyerId;}

	public void setBuyerId(int buyerId) {this.buyerId = buyerId;}

	public String getPrice() {
		Map<String, Integer> map = staticsService.getTheTotalPriceMonth();
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	
	public String getType() {
		Map<String, Integer> map = staticsService.getTypeData();
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
	
	public String getBuyerData() {
		Map<Integer, Integer> map = staticsService.getBuyerData(buyerId);
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		return SUCCESS;
	}
}
