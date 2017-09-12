package dao.impl;

import java.util.List;

import model.Book;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.BookDao;

public class BookDaoImpl extends HibernateDaoSupport implements BookDao {

	public Integer save(Book book) {
		return (Integer) getHibernateTemplate().save(book);
	}

	public void delete(Book book) {
		getHibernateTemplate().delete(book);
	}

	public void update(Book book) {
		getHibernateTemplate().merge(book);
	}

	public Book getBookById(int id) {
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) getHibernateTemplate().find(
				"from Book as b where b.id=?", id);
		Book book = books.size() > 0 ? books.get(0) : null;
		return book;
	}

	public List<Book> getAllBooks(String key) {
		if (key == null)
			key = "";
		@SuppressWarnings("unchecked")
		String hql="from Book as b where b.title like '%"+key+"%'";
		List<Book> books = (List<Book>) getHibernateTemplate().find(hql);
		return books;
	}

}
