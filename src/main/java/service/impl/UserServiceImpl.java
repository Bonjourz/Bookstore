package service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

import dao.AddressDao;
import dao.UserDao;
import dao.FileDao;
import model.Buyer;
import service.UserService;
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	private AddressDao addressDao;
	private FileDao fileDao;
	private MongoTemplate mongoTemplate;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}
	
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public Integer addUser(Buyer user) {
		return userDao.save(user);
	}

	public void deleteUser(Buyer user) {
		userDao.delete(user);
	}

	public void updateUser(Buyer user) {
		userDao.update(user);
	}

	public Buyer getUserById(int id) {
		return userDao.getUserById(id);
	}

	public List<Buyer> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public Buyer login_buyer(String email, String password) {
		return userDao.login_buyer(email, password);
	}
	
	public Buyer login_admin(String username, String password) {
		return userDao.login_admin(username, password);
	}
	
	public boolean modifyPwd(int buyerId, String oldPwd, String newPwd) {
		Buyer buyer = userDao.getUserById(buyerId);
		String old = buyer.getPassword();
		if (!old.equals(oldPwd))
			return false;
		
		buyer.setPassword(newPwd);
		userDao.save(buyer);
		return true;
	}
	
	public boolean insertAddress(int userId, String address) {
		return addressDao.insertAddress(userId, address);
	}

	public boolean deleteAddress(int userId, String address) {
		return addressDao.deleteAddress(userId, address);
	}

	public List<String> getAddressById(int userId) {
		return addressDao.getAddressById(userId);
	}
	
	public boolean checkEmail(String email) {
		Buyer buyer = userDao.getBuyerByEmail(email);
		if (buyer == null)
			return true;
		else
			return false;
	}

	@Override
	public boolean register(String userName, String password, String email) {
		if (!checkEmail(email))
			return false;
		
		/* Insert into mysql */
		Buyer buyer = new Buyer();
		buyer.setEmail(email);
		buyer.setPassword(password);
		buyer.setRole("buyer");
		buyer.setUsername(userName);
		int buyerId = userDao.save(buyer);
		
		/* Insert into mongoDB */
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", buyerId);
		map.put("address", "");
		
		DB db = mongoTemplate.getDb();
		DBCollection bookStore = db.getCollection("Users");
		DBObject newBuyer= new BasicDBObject(map);
		bookStore.insert(newBuyer);
		
		return true;
	}

	@Override
	public void storeImg(int buyerId, InputStream inputStream) {
		fileDao.storeImg(buyerId, inputStream);
	}

	@Override
	public GridFSDBFile getImg(int buyerId) {
		return fileDao.getImg(buyerId);
	}
}
