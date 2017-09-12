package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.StaticsDao;
import model.Orderitem;
import model.Statics;

public class StaticsDaoImpl extends HibernateDaoSupport implements StaticsDao {
	
	public List<Statics> getAllStatics() {
		@SuppressWarnings("unchecked")
		List<Statics> statics_list = (List<Statics>) getHibernateTemplate()
				.find("from Statics");
		return statics_list.size() == 0 ? null : statics_list;
	}

	@Override
	public List<Statics> getBuyerStatics(int buyerId) {
		@SuppressWarnings("unchecked")
		List<Statics> statics_list = (List<Statics>) getHibernateTemplate()
				.find("from Statics as s where s.buyerid=?", buyerId);
		return statics_list.size() == 0 ? null : statics_list;
	}
	
}
