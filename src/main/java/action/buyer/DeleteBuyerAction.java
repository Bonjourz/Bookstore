package action.buyer;

import action.BaseAction;
import model.Buyer;
import service.UserService;

public class DeleteBuyerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;

	private UserService userService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception {

		Buyer user = userService.getUserById(id);
		userService.deleteUser(user);

		return SUCCESS;
	}

}
