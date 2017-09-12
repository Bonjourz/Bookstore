package dao;

import java.io.File;
import java.util.List;

import model.Buyer;

public interface InfoDao {

	public void update(int bookid, String filename);

	public List<Buyer> findAll();
}
