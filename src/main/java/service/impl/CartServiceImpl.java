package service.impl;

import java.util.List;

import model.Cart;
import service.CartService;
import dao.BookDao;
import dao.CartDao;
import service.BookService;

public class CartServiceImpl implements CartService {
	private BookService bookService;
	
	private CartDao cartDao;
	
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public Integer addCart(Cart cart) {
		return cartDao.save(cart);
	}

	public void deleteCart(Cart cart) {
		cartDao.delete(cart);
	}

	public void updateCart(Cart cart) {
		cartDao.update(cart);
	}

	public List<Cart> getCartsByBuyer(int buyerId) {
		List<Cart> carts = cartDao.getCartsByBuyer(buyerId);
		for (int i = 0; i < carts.size(); i++) {
			carts.get(i).setBook(bookService.getBookById(carts.get(i).getBookid()));
		}
		return carts;
	}

	public Cart getCartById(int id) {
		Cart cart = cartDao.getCartById(id);
		cart.setBook(bookService.getBookById(cart.getBookid()));
		return cartDao.getCartById(id);
	}

	public List<Cart> getAllCarts() {
		List<Cart> carts = cartDao.getAllCarts();
		for (int i = 0; i < carts.size(); i++) {
			carts.get(i).setBook(bookService.getBookById(carts.get(i).getBookid()));
		}
		return carts;
	}
}
