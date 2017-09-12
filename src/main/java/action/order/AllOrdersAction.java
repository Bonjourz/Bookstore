package action.order;

import java.util.List;

import action.BaseAction;
import model.Book;
import model.Order;
import model.Buyer;
import service.BookService;
import service.OrderService;
import service.UserService;

public class AllOrdersAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BookService bookService;
	
	private UserService userService;
	
	private OrderService orderService;

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public String execute() throws Exception {
		String role = (String)session().getAttribute("role");
		List<Order> orders;
		
		if (role.equals("admin")) {
			orders = orderService.getAllOrders();
			List<Book> books = bookService.getAllBooks("");
			List<Buyer> buyers = userService.getAllUsers();
			session().setAttribute("orders", orders);
			session().setAttribute("books", books);
			session().setAttribute("buyers", buyers);
			System.out.println(orders.size());
		}
		
		else if(role.equals("buyer")){
			orders = orderService.getOrdersByBuyer((Integer)session().getAttribute("id"));
			session().setAttribute("orders", orders);
		}
		
		return SUCCESS;
	}
}
