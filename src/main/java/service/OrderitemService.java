package service;

import java.util.List;

import model.Orderitem;

public interface OrderitemService {
	public Integer addOrderitem(Orderitem orderitem);

	public void deleteOrderitem(Orderitem orderitem);

	public void updateOrderitem(Orderitem orderitem);

	public Orderitem getOrderitemById(int id);
	
	public List<Orderitem> getOrderItemsByOrderId(int orderId);

	public List<Orderitem> getAllOrderitems();

}
