package action.order;
import action.BaseAction;
import model.Order;
import service.OrderService;

public class UpdateBuyerOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;
	private String status;

	private OrderService orderService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public String getStatus() {return status;}

	public void setStatus(String status) {this.status = status;}


	@Override
	public String execute() throws Exception {

		Order order = orderService.getOrderById(id);
		order.setStatus(status);
		orderService.updateOrder(order);
		System.out.println("fuck");
		return SUCCESS;
	}
}
