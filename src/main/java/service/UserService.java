package service;

import java.io.InputStream;
import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;

import model.Buyer;

public interface UserService {
	public Integer addUser(Buyer user);

	public void deleteUser(Buyer user);

	public void updateUser(Buyer user);
	
	public Buyer login_buyer(String email, String password);
	
	public Buyer login_admin(String username, String password);

	public Buyer getUserById(int id);

	public List<Buyer> getAllUsers();
	
	public boolean modifyPwd(int buyerId, String oldPwd, String newPwd);
	
	public boolean insertAddress(int userId, String address);
	
	public boolean deleteAddress(int userId, String address);
	
	public List<String> getAddressById(int userId);
	
	public boolean checkEmail(String email);
	
	public boolean register(String userName, String password, String Email);
	
	/**
	 * Upload Img
	 */
	
	public void storeImg(int buyerId, InputStream inputStream);
	
	public GridFSDBFile getImg(int buyerId);
}
