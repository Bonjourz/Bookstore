package action.login;

import action.BaseAction;

public class LogoutAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		session().setAttribute("role", null);
		session().setAttribute("id", null);
		return SUCCESS;
	}
}
