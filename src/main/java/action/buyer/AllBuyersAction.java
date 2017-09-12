package action.buyer;

import java.util.List;

import action.BaseAction;
import model.Buyer;
import service.UserService;

public class AllBuyersAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception {
		
		List<Buyer> users = userService.getAllUsers();
		request().setAttribute("users", users);

		return SUCCESS;
	}
}
