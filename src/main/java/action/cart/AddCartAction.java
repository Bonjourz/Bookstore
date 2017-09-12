package action.cart;

import java.util.List;
import action.BaseAction;
import model.Cart;
import model.Book;
import service.CartService;


public class AddCartAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	

	private int bookId;
	private String result;
	private CartService cartService;
	
	public String getResult() {return result;}

	public void setResult(String result) {this.result = result;}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public int getBookId() {
		return bookId;
	}
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Override
	public String execute() throws Exception {
		int buyerid = (Integer)session().getAttribute("id");
		List<Cart> carts = cartService.getCartsByBuyer(buyerid);
		boolean flag = true;
		for (int i = 0; i < carts.size(); i++) {
			if (bookId == carts.get(i).getBookid()) {
				flag = false;
				break;
			}		
		}
		
		if (flag) {
			Cart cart = new Cart(buyerid, bookId, 1);
			cartService.addCart(cart);
			result = "Add Successfully!";
		}
		
		else {
			result = "You have added this book!!!";
		}

		return SUCCESS;
	}
	
}
