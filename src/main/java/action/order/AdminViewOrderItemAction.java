package action.order;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

import action.BaseAction;
import model.Orderitem;
import net.sf.json.JSONObject;
import service.OrderitemService;

public class AdminViewOrderItemAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private OrderitemService orderitemService;
	private String result;
	
	public int getOrderId() {return orderId;}
	
	public void setOrderId(int orderId) {this.orderId = orderId;}
	
	public String getResult() {return result;}

	public void setResult(String result) {this.result = result;}

	public void setOrderitemService(OrderitemService orderitemService) {
		this.orderitemService = orderitemService;
	}
	
	@Override
	public String execute() throws Exception {
		List<Orderitem> orderitems = orderitemService.getOrderItemsByOrderId(orderId);
		System.out.println(orderId);
		ArrayList<Map> mapList = new ArrayList<Map>();
		for (int i = 0; i < orderitems.size(); i++) {
			Orderitem orderitem = orderitems.get(i);
			Map<String, Object> mapEle = new HashMap<String, Object>();
			mapEle.put("bookname", orderitem.getBook().getTitle());
			mapEle.put("num", orderitem.getAmount());
			mapEle.put("totalPrice", orderitem.getPrice());
			mapList.add(mapEle);
		}
		System.out.println("finish");
		
	
		HashMap<String, Object> dataMap = new HashMap<String, Object>();  
        dataMap.put("orderitems", mapList);
		System.out.println("end");
		JSONObject json = JSONObject.fromObject(dataMap);
		result = json.toString();
		return SUCCESS;
	}

	
}
