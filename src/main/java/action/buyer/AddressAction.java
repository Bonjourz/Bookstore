package action.buyer;

import java.util.List;

import action.BaseAction;
import net.sf.json.JSONObject;
import service.UserService;

public class AddressAction extends BaseAction {
	private static final long serialVersionUID = 1L;	
	private String address;
	private int buyerId;
	private String result;
	private UserService userService;
	
	public String getAddress() {return address;}
	
	public void setAddress(String address) {this.address = address;}

	public int getBuyerId() {return buyerId;}

	public void setBuyerId(int buyerId) {this.buyerId = buyerId;}

	public String getResult() {return result;}

	public void setResult(String result) {this.result = result;}

	public void setUserService(UserService userService) {this.userService = userService;}

	public String getAddressList() {
		List<String> addressList = userService.getAddressById(buyerId);
		result = addressList.toString();
		return SUCCESS;
	}
	
	public String deleteAddress() {
		userService.deleteAddress(buyerId, address);
		return SUCCESS;
	}
	
	public String addAddress() {
		boolean flag = userService.insertAddress(buyerId, address);
		
		if (flag)
			result = "Sucess!";
		else
			result = "Duplicate Address!";
		return SUCCESS;
	}
}
