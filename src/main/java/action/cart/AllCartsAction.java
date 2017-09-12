package action.cart;

import java.util.List;

import action.BaseAction;

import service.CartService;

import model.Cart;


public class AllCartsAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private CartService cartService;
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@Override
	public String execute() throws Exception {
		if (session().getAttribute("id") != null) {
			int buyerId = (Integer)session().getAttribute("id");
			System.out.println(buyerId);
			System.out.println("sucess");
			List<Cart> carts = cartService.getCartsByBuyer(buyerId);
			session().setAttribute("carts", carts);
		}	
		

		return SUCCESS;
	}
}
