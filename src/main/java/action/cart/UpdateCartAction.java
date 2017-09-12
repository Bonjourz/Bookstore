package action.cart;

import action.BaseAction;
import model.Cart; 
import model.Book;
import service.CartService;

public class UpdateCartAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;
	private int num;
	
	private CartService cartService;

	public int getId() {return id;}

	public void setId(int id) {this.id = id;}
	
	public int getNum() {return num;}
	
	public void setNum(int num) {this.num = num;}
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	@Override
	public String execute() throws Exception {
		Cart cart = cartService.getCartById(id);
		cart.setNum(num);
		cartService.updateCart(cart);
		
		return SUCCESS;
	}

}
