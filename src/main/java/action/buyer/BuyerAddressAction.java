package action.buyer;

import action.BaseAction;

import service.UserService;

public class BuyerAddressAction extends BaseAction {
	
	private UserService userService;
	
	private String address;
	
	private int userId;
	
	private int addressId;

	public void setUserService(UserService userService) {this.userService = userService;}

	public int getUserId() {return userId;}

	public void setUserId(int userId) {this.userId = userId;}

	public String getAddress() {return address;}

	public void setAddress(String address) {this.address = address;}

	public int getAddressId() {return addressId;}

	public void setAddressId(int addressId) {this.addressId = addressId;}
	
	@Override
	public String execute() throws Exception {
		userService.insertAddress(userId, "a");
		return SUCCESS;
	}

	
}
