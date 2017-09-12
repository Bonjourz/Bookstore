package action.buyer;

import action.BaseAction;
import service.UserService;

public class RegisterAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String userName;
	private String password;
	private String result;
	private UserService userService;
	
	public void setUserService(UserService userService) {this.userService = userService;}

	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}

	public String getUserName() {return userName;}

	public void setUserName(String userName) {this.userName = userName;}

	public String getPassword() {return password;}

	public void setPassword(String password) {this.password = password;}
	
	public String getResult() {return result;}

	public void setResult(String result) {this.result = result;}

	public String checkEmail() {
		boolean flag = userService.checkEmail(email);
		if (flag)
			result = "This email can be registered.";
		else
			result = "This email has been used!";
		return SUCCESS;
	}
	
	public String register() {
		boolean flag = userService.register(userName, password, email);
		if (!flag)
			result = "The email is invalid!";
		else
			result = "Register successfully!";
		return SUCCESS;
	}
}
