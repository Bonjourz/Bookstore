package action.file;

import model.Buyer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import com.mongodb.gridfs.GridFSDBFile;

import action.BaseAction;
import service.UserService;

public class ImgAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private File file;
	
	public void setUserService(UserService userService) {this.userService = userService;}
	
	public File getFile() {return file;}
	
	public void setFile(File file) {this.file = file;}
	
	public String uploadImg() throws IOException, IllegalStateException, ServletException {
		InputStream fileStream = new FileInputStream(file);
		int buyerId = (Integer) session().getAttribute("id");
		userService.storeImg(buyerId, fileStream);
		return SUCCESS;
	}
	
	public String getImg() throws IOException {
		int buyerId = (Integer) session().getAttribute("id");
		GridFSDBFile file = userService.getImg(buyerId);
		OutputStream out = response().getOutputStream();
		file.writeTo(out);
		out.flush();
		out.close();
		return null;
	}

}
