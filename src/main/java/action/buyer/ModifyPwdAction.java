package action.buyer;

import action.BaseAction;
import service.UserService;

public class ModifyPwdAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private int buyerId;
	private String oldPwd;
	private String newPwd;
	private String result;
	private UserService userService;

	public int getBuyerId() {return buyerId;}

	public void setBuyerId(int buyerId) {this.buyerId = buyerId;}

	public String getOldPwd() {return oldPwd;}

	public void setOldPwd(String oldPwd) {this.oldPwd = oldPwd;}

	public String getNewPwd() {return newPwd;}

	public void setNewPwd(String newPwd) {this.newPwd = newPwd;}
	
	public String getResult() {return result;}

	public void setResult(String result) {this.result = result;}

	public void setUserService(UserService userService) {this.userService = userService;}
	
	@Override
	public String execute() throws Exception {
		boolean flag = userService.modifyPwd(buyerId, oldPwd, newPwd);
		if (flag)
			result = "success";
		else
			result = "fail";
		
		return SUCCESS;
	}

}
