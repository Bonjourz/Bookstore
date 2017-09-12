package action.buyer;

import action.BaseAction;
import model.Buyer;
import service.UserService;

public class AddBuyerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String email;

	private UserService userService;

	public String getUsername() {return username;}

	public void setUsername(String username) {this.username = username;}

	public String getPassword() {return password;}

	public void setPassword(String password) {this.password = password;}
	
	public String getEmail() {return email;}
	
	public void setEmail(String email) {this.email = email;}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception {
		Buyer buyer = new Buyer(username, password, "buyer", email);
		userService.addUser(buyer);

		return SUCCESS;
	}

}
