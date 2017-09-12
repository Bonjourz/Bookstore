package action.buyer;

import action.BaseAction;
import model.Buyer;
import service.UserService;

public class UpdateBuyerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String password;
	private String email;

	private UserService userService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String execute() throws Exception {

		Buyer user = userService.getUserById(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		userService.updateUser(user);

		return SUCCESS;
	}

}
