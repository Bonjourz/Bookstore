package dao;

import java.util.List;
import java.util.Map;

public interface AddressDao {
	public boolean insertAddress(int userId, String address);

	public boolean deleteAddress(int userId, String address);

	public List<String> getAddressById(int userId);
}
