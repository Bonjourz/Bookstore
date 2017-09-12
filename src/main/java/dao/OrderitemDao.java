package dao;

import java.util.List;

import model.Orderitem;

import model.Order;

public interface OrderitemDao {

	public Integer save(Orderitem orderitem);

	public void delete(Orderitem orderitem);

	public void update(Orderitem orderitem);

	public Orderitem getOrderitemById(int id);

	public List<Orderitem> getOrderByOrderId(int orderId);

	public List<Orderitem> getAllOrderitems();

}