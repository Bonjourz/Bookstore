package action.order;

import java.sql.Date;

import action.BaseAction;
import model.Order;
import service.OrderService;

public class UpdateOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;
	private String address;
	private String status;

	private OrderService orderService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setAppService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public String getAddress() {return address;}

	public void setAddress(String address) {this.address = address;}
	
	public String getStatus() {return status;}

	public void setStatus(String status) {this.status = status;}


	@Override
	public String execute() throws Exception {

		Order order = orderService.getOrderById(id);
		order.setStatus(status);
		order.setAddress(address);
		orderService.updateOrder(order);

		return SUCCESS;
	}
}
