package action.cart;

import action.BaseAction;
import model.Cart; 
import service.CartService;

public class DeleteCartAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;

	private CartService cartService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	@Override
	public String execute() throws Exception {

		Cart cart = cartService.getCartById(id);
		cartService.deleteCart(cart);

		return SUCCESS;
	}

}
