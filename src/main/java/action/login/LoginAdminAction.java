package action.login;

import action.BaseAction;
import model.Buyer;
import service.UserService;

public class LoginAdminAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private UserService userService;
	private String result;
	
	public void setUsername(String username) {this.username = username;}
	
	public String getUsername() {return username;}
	
	public void setPassword(String password) {this.password = password;}
	
	public String getPassword() {return password;}
	
	public String getResult() {return result;}

	public void setResult(String result) {this.result = result;}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public String execute() throws Exception {
		Buyer admin = userService.login_admin(username, password);
		
		if (admin == null) {
			result = "error";
			return SUCCESS;
		}
		
		else {
			session().setAttribute("role", "admin");
			session().setAttribute("id", admin.getId());
			result = "success";
			System.out.println(result);
			return SUCCESS;
		}
	}
}
