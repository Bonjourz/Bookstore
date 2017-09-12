package service;

import java.util.List;

import model.Order;

public interface OrderService {
	public Integer addOrder(Order order);

	public void deleteOrder(Order order);

	public void updateOrder(Order order);
	
	public void generateOrder(int buyerId, String address, List<String> cartIdList);

	public Order getOrderById(int id);
	
	public List<Order> getOrdersByBuyer(int buyerId);

	public List<Order> getAllOrders();

	public void addSingleOrderItem(int buyerId, int bookId , int num, String address);
}
