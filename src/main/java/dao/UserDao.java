package dao;

import java.util.List;

import model.Buyer;

public interface UserDao {

	public Integer save(Buyer user);

	public void delete(Buyer user);

	public void update(Buyer user);

	public Buyer getUserById(int id);
	
	public Buyer login_buyer(String email, String password);
	
	public Buyer login_admin(String username, String password);
	
	public Buyer getBuyerByEmail(String email);

	public List<Buyer> getAllUsers();

}