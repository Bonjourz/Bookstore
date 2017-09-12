package service;

import java.util.List;

import model.Cart;

public interface CartService {
public Integer addCart(Cart cart);
	
	public void deleteCart(Cart cart);

	public void updateCart(Cart cart);
	
	public List<Cart> getCartsByBuyer(int buyerId);

	public Cart getCartById(int id);

	public List<Cart> getAllCarts();
}
