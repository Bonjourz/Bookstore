package action.authorization;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;  

public class checkBuyer extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		String role = (String)session.get("role");
		if (role == null) {
			return "login";
		}
		else if(role.equals("buyer")) {
			return invocation.invoke();
		}
		
		else {
			return "login_buyer";
		}
	}

}
