package dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import dao.AddressDao;

public class AddressDaoImpl implements AddressDao{
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public MongoTemplate getMongoTemplate() {
		return this.mongoTemplate;
	}

	@Override
	public boolean insertAddress(int userId, String address) {
		DB db = mongoTemplate.getDb();
		DBCollection bookStore = db.getCollection("Users");

		DBObject dbObj;
		BasicDBObject query = new BasicDBObject();
		query.put("userId", userId);
		
		/* If cannot match record with the provided id */
		if ((dbObj = bookStore.findOne(query)) == null)
			return false;
		
		Map<String,Object> dataMap = dbObj.toMap();
		List<String> addressList =  (List<String>) dataMap.get("address");
		
		/* Duplicate address */
		if (addressList.contains(address))
			return false;
		
		addressList.add(address);
		dataMap.put("address", addressList);
		
		/* Update the mongoDB */
		bookStore.update(query, new BasicDBObject(dataMap), false, true);
		return true;
	}

	@Override
	public boolean deleteAddress(int userId, String address) {
		DB db = mongoTemplate.getDb();
		DBCollection bookStore = db.getCollection("Users");
		
		DBObject dbObj;
		BasicDBObject query = new BasicDBObject();
		query.put("userId", userId);
		
		/* If cannot match record with the provided id */
		if ((dbObj = bookStore.findOne(query)) == null)
			return false;
		
		Map<String,Object> dataMap = dbObj.toMap();
		List<String> addressList =  (List<String>) dataMap.get("address");
		
		/* Check if contains the address */
		if (!addressList.contains(address))
			return false;
		
		addressList.remove(address);
		dataMap.put("address", addressList);
		bookStore.update(query, new BasicDBObject(dataMap), false, true);
		return true;
	}

	@Override
	public List<String> getAddressById(int userId) {
		DB db = mongoTemplate.getDb();
		DBCollection bookStore = db.getCollection("Users");
		
		DBObject dbObj;
		BasicDBObject query = new BasicDBObject();
		query.put("userId", userId);
		
		/* If cannot match record with the provided id */
		if ((dbObj = bookStore.findOne(query)) == null)
			return null;
		
		Map<String,Object> dataMap = dbObj.toMap();
		List<String> addressList =  (List<String>) dataMap.get("address");
		return addressList;
	}

}
