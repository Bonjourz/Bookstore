package action.login;

import action.BaseAction;
import model.Buyer;
import service.UserService;

public class LoginBuyerAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private UserService userService;
	private String result;
	
	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}
	
	public void setPassword(String password) {this.password = password;}
	
	public String getPassword() {return password;}
	
	public String getResult() {return result;}

	public void setResult(String result) {this.result = result;}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public String execute() throws Exception {
		Buyer buyer = userService.login_buyer(email, password);
		
		if (buyer == null) {
			result = "error";
			return SUCCESS;
		}
		
		else {
			session().setAttribute("role", "buyer");
			session().setAttribute("id", buyer.getId());
			result = "success";
			System.out.println(result);
			return SUCCESS;
		}
	}
}
