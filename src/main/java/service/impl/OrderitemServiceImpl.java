package service.impl;

import java.util.List;

import dao.OrderitemDao;
import model.Orderitem;
import service.OrderitemService;
import service.BookService;
public class OrderitemServiceImpl implements OrderitemService {
	private OrderitemDao orderitemDao;
	private BookService bookService;

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public void setOrderitemDao(OrderitemDao orderitemDao) {
		this.orderitemDao = orderitemDao;
	}
	
	public Integer addOrderitem(Orderitem orderitem) {
		return orderitemDao.save(orderitem);
	}
	
	

	public void deleteOrderitem(Orderitem orderitem) {
		orderitemDao.delete(orderitem);
	}

	public void updateOrderitem(Orderitem orderitem) {
		orderitemDao.update(orderitem);
	}

	public Orderitem getOrderitemById(int id) {
		Orderitem orderitem = orderitemDao.getOrderitemById(id);
		if (orderitem != null)
			orderitem.setBook(bookService.getBookById(orderitem.getBookid()));
			
		return orderitem;
	}

	public List<Orderitem> getOrderItemsByOrderId(int orderId){
		List<Orderitem> orderitems = orderitemDao.getOrderByOrderId(orderId);
		for (int i = 0; i < orderitems.size(); i++) 
			orderitems.get(i).setBook(bookService.getBookById(orderitems.get(i).getBookid()));
		
		return orderitems;
	}
	
	public List<Orderitem> getAllOrderitems() {
		return orderitemDao.getAllOrderitems();
	}
}
