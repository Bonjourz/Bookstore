package dao.impl;

import java.io.IOException;

import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import dao.FileDao;

public class FileDaoImpl implements FileDao {
	private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {return mongoTemplate;}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {this.mongoTemplate = mongoTemplate;}
	
	@Override
	public void storeImg(int buyerId, InputStream inputStream) {
		DB db = mongoTemplate.getDb();
		DBCollection collection = db.getCollection("userImg");  
		GridFS gfsPhoto = new GridFS(db, "userImg");
		DBObject query  = new BasicDBObject("_id", buyerId);  
	    GridFSDBFile gridFSDBFile = gfsPhoto.findOne(query);  
	    if (gridFSDBFile != null)
	    	gfsPhoto.remove(query);

        gfsPhoto = new GridFS(db, "userImg"); //connection为集合名词  
		GridFSInputFile gfsFile = null;  
		gfsFile = gfsPhoto.createFile(inputStream);
		gfsFile.setId(buyerId);  
		gfsFile.setContentType("image/jpeg");  
		gfsFile.save();
		
	}

	@Override
	public GridFSDBFile getImg(int buyerId) {
		DB db = mongoTemplate.getDb(); 
		GridFS gfsPhoto = new GridFS(db, "userImg");
		DBObject query  = new BasicDBObject("_id", buyerId);  
	    GridFSDBFile gridFSDBFile = gfsPhoto.findOne(query);
        return gridFSDBFile;
	}

	
	
}
