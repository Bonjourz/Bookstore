package service;

import java.util.List;

import model.Book;

public interface BookService {
	public Integer addBook(Book book);

	public void deleteBook(Book book);

	public void updateBook(Book book);

	public Book getBookById(int id);

	public List<Book> getAllBooks(String key);
}
