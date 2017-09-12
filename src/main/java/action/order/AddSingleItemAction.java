package action.order;

import java.text.SimpleDateFormat;
import java.util.Date;

import action.BaseAction;
import model.Order;
import service.OrderService;
import model.Buyer;


public class AddSingleItemAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	private OrderService orderService;
	
	private int bookId;
	
	private int num;
	
	public int getNum() {return num;}

	public void setNum(int num) {this.num = num;}

	public int getBookId() {return bookId;}

	public void setBookId(int bookId) {this.bookId = bookId;}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public String execute() throws Exception {
		int buyerId = (Integer)session().getAttribute("id");
		String address = "sjtu";
		orderService.addSingleOrderItem(buyerId, bookId , num, address);

		return SUCCESS;
	}

}
