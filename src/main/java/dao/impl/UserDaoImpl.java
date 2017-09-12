package dao.impl;

import java.util.List;

import model.Buyer;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public Integer save(Buyer user) {
		return (Integer) getHibernateTemplate().save(user);
	}

	public void delete(Buyer user) {
		getHibernateTemplate().delete(user);
	}

	public void update(Buyer user) {
		getHibernateTemplate().merge(user);
	}
	
	public Buyer login_buyer(String email, String password) {
		@SuppressWarnings("unchecked")
		List<Buyer> users = (List<Buyer>)getHibernateTemplate().find(
				"from Buyer as u where u.email= ? and password = ?", email, password);
		Buyer user = users.size() > 0 ? users.get(0) : null;
		return user;
	}
	
	public Buyer login_admin(String username, String password) {
		@SuppressWarnings("unchecked")
		List<Buyer> users = (List<Buyer>)getHibernateTemplate().find(
				"from Buyer as u where u.username= ? and password = ?", username, password);
		Buyer user = users.size() > 0 ? users.get(0) : null;
		return user;
	}

	public Buyer getUserById(int id) {
		@SuppressWarnings("unchecked")
		List<Buyer> users = (List<Buyer>) getHibernateTemplate().find(
				"from Buyer as u where u.id=?", id);
		Buyer user = users.size() > 0 ? users.get(0) : null;
		return user;
	}

	public List<Buyer> getAllUsers() {
		@SuppressWarnings("unchecked")
		List<Buyer> users = (List<Buyer>) getHibernateTemplate()
				.find("from Buyer as b where b.role= ?", "buyer");
		return users;
	}

	@Override
	public Buyer getBuyerByEmail(String email) {
		List<Buyer> users = (List<Buyer>) getHibernateTemplate().find(
				"from Buyer as u where u.email=?", email);
		Buyer buyer = users.size() > 0 ? users.get(0) : null;
		return buyer;
	}

}
