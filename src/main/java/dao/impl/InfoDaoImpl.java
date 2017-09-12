package dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;

import dao.InfoDao;
import model.Buyer;

public class InfoDaoImpl implements InfoDao {
	
	private MongoTemplate mongoTemplate;
	
	private String collectionName;
	 
	public MongoTemplate getMongoTemplate() {return mongoTemplate;}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {this.mongoTemplate = mongoTemplate;}
	
	public String getCollectionName() {return collectionName;}

	public void setCollectionName(String collectionName) {this.collectionName = collectionName;}

	@Override
	/*
	public void insert(String collectionName, File file, String fileid, String companyid, String filename) {
		try {
            DB db = mongoTemplate.getDb();
            // 存储fs的根节点
            GridFS gridFS = new GridFS(db, collectionName);
            GridFSInputFile gfs = gridFS.createFile(file);
            gfs.put("aliases", companyid);
            gfs.put("filename", fileid);
            gfs.put("contentType", filename.substring(filename.lastIndexOf(".")));
            gfs.save();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("存储文件时发生错误！！！");
        }
		
	}
	*/
	
	public void update(int bookid, String filename) {
		Map<String, Object> documentMap = new HashMap<String, Object>();
		documentMap.put("bookid", bookid);
		File file = new File(filename);
		documentMap.put("file", file);

		DB db = mongoTemplate.getDb();
		DBCollection bookimg = db.getCollection("bookimg");
		bookimg.insert(new BasicDBObject(documentMap));
	}

	@Override
	public List<Buyer> findAll() {
		
		return null;
	}
}
