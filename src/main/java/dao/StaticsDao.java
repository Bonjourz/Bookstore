package dao;

import java.util.List;
import model.Statics;

public interface StaticsDao {

	public List<Statics> getAllStatics();

	public List<Statics> getBuyerStatics(int buyerId);
}
