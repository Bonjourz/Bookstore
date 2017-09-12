package action.book;

import java.sql.Date;

import action.BaseAction;
import model.Book;
import service.BookService;

public class UpdateBookAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String author;
	private int price;
	private String publisher;
	private int stock;
	private String type;

	private BookService bookService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public int getStock() {return stock;}

	public void setStock(int stock) {this.stock = stock;}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public String execute() throws Exception {

		Book book = bookService.getBookById(id);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(price);
		book.setPublisher(publisher);
		book.setStock(stock);
		book.setType(type);
		bookService.updateBook(book);

		return SUCCESS;
	}

}
