package action.info;

import java.util.List;

import action.BaseAction;
import model.Buyer;
import service.UserService;

public class BuyerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception {
		Buyer buyer = userService.getUserById((Integer)session().getAttribute("id"));
		session().setAttribute("buyer", buyer);
		return SUCCESS;
	}
}
