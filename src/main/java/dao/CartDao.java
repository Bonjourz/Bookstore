package dao;

import java.util.List;

import model.Cart;

public interface CartDao {
	public Integer save(Cart cart);

	public void delete(Cart cart);

	public void update(Cart cart);

	public Cart getCartById(int id);

	public List<Cart> getCartsByBuyer(int buyerId);

	public List<Cart> getAllCarts();

	public List<Cart> getCartsByCartIdList(List<String> cartIdList);
}
