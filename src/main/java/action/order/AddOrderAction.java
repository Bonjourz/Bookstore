package action.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import action.BaseAction;
import model.Order;
import net.sf.json.JSONArray;
import service.OrderService;
import model.Buyer;


public class AddOrderAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	private OrderService orderService;
	
	private String address;
	
	private String cartId;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public String getCartId() {return cartId;}

	public void setCartId(String cartId) {this.cartId = cartId;}
	
	public void setAddress(String address) {this.address = address;}

	@Override
	public String execute() throws Exception {
		int buyerId = (Integer)session().getAttribute("id");
		JSONArray json = JSONArray.fromObject(cartId);
		List<String> cartId_list = json.toList(json);
		orderService.generateOrder(buyerId, address, cartId_list);
		return SUCCESS;
	}

}
