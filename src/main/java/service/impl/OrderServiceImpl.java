package service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.BookDao;
import dao.CartDao;
import dao.OrderDao;
import dao.OrderitemDao;
import model.Book;
import model.Cart;
import model.Order;
import model.Orderitem;
import service.OrderService;
import service.OrderitemService;
import service.BookService;
import service.CartService;
import service.UserService;
public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	private CartDao cartDao;
	private BookDao bookDao;
	private OrderitemDao orderitemDao;
	private BookService bookService;
	private CartService cartService;
	private OrderitemService orderitemService;
	private UserService userService;
	
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public void setOrderitemDao(OrderitemDao orderitemDao) {
		this.orderitemDao = orderitemDao;
	}
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	public void setOrderitemService(OrderitemService orderitemService) {
		this.orderitemService = orderitemService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public Integer addOrder(Order order) {
		return orderDao.save(order);
	}

	public void deleteOrder(Order order) {
		orderDao.delete(order);
	}

	public void updateOrder(Order order) {
		orderDao.update(order);
	}
	
	public void generateOrder(int buyerId, String address, List<String> cartIdList) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(date);
	    
		Order order = new Order(buyerId, dateString, address, "payed", 0);		
		int orderId = addOrder(order);
		
		List<Cart> carts = new ArrayList<Cart>();
		
		for (String str : cartIdList) 
			carts.add(cartService.getCartById(Integer.valueOf(str)));
		
		double total_price = 0;
		for (int i = 0; i< carts.size(); i++) {
			Cart cart = carts.get(i);
			Book book = cart.getBook();
			int bookNum = cart.getNum();
			double price = book.getPrice();
			double total = price * bookNum;
			total_price += total;
			
			book.setStock(book.getStock() - bookNum);
			Orderitem orderItem = new Orderitem(orderId, book.getId(), bookNum, total);
			bookDao.save(book);
			orderitemDao.save(orderItem);
		}
		
		for(int j = 0; j< carts.size(); j++) {
			cartDao.delete(carts.get(j));
		}
		
		order = orderDao.getOrderById(orderId);
		order.setPrice(total_price);
		orderDao.update(order);
	}

	public Order getOrderById(int id) {
		return orderDao.getOrderById(id);
	}
	
	public List<Order> getOrdersByBuyer(int buyerId) {
		return orderDao.getOrdersByBuyer(buyerId);
	}

	public List<Order> getAllOrders() {
		List<Order> orders = orderDao.getAllOrders();
		for (int i = 0; i < orders.size(); i++) {
			int buyerid= orders.get(i).getBuyerid();
			orders.get(i).setBuyer(userService.getUserById(buyerid));
		}
		return orders;
	}
	
	public void addSingleOrderItem(int buyerId, int bookId, int num, String address) {
		double price = bookService.getBookById(bookId).getPrice();
		price *= num;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(date);
		Order order = new Order(buyerId, dateString, address, "payed", price);
		int orderId = addOrder(order);
		Orderitem orderitem = new Orderitem(orderId, bookId, num, price);
		orderitemService.addOrderitem(orderitem);
	}
}
