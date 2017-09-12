package dao;

import java.io.InputStream;
import java.io.OutputStream;

import com.mongodb.gridfs.GridFSDBFile;

public interface FileDao {
	public void storeImg(int buyerId, InputStream inputStream);

	public GridFSDBFile getImg(int buyerId);
}
