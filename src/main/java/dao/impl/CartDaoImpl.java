package dao.impl;

import java.util.ArrayList;
import java.util.List;

import model.Cart;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.CartDao;

public class CartDaoImpl extends HibernateDaoSupport implements CartDao  {

	public Integer save(Cart cart) {
		return (Integer) getHibernateTemplate().save(cart);
	}

	public void delete(Cart cart) {
		getHibernateTemplate().delete(cart);
	}

	public void update(Cart cart) {
		getHibernateTemplate().merge(cart);
	}

	public Cart getCartById(int id) {
		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) getHibernateTemplate().find(
				"from Cart as c where c.id=?", id);
		Cart cart = carts.size() > 0 ? carts.get(0) : null;
		return cart;
	}
	
	public List<Cart> getCartsByBuyer(int buyerId) {
		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) getHibernateTemplate().find(
				"from Cart as c where c.buyerid=?", buyerId);
		return carts;
	}

	public List<Cart> getAllCarts() {
		@SuppressWarnings("unchecked")
		List<Cart> carts = (List<Cart>) getHibernateTemplate()
				.find("from Cart");
		return carts;
	}

	@Override
	public List<Cart> getCartsByCartIdList(List<String> cartIdList) {
		List<Cart> carts = new ArrayList<Cart>();
		for (int i = 0; i < cartIdList.size(); i++) {
			int id = Integer.valueOf((cartIdList.get(i)));
			carts.add(getCartById(id));
		}
		return carts;
	}

}
