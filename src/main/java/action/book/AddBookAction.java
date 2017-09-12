package action.book;

import java.sql.Date;
import java.text.SimpleDateFormat;

import action.BaseAction;
import model.Book;
import service.BookService;

public class AddBookAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String title;
	private String author;
	private int price;
	private String publisher;
	private int stock;
	private String type;

	private BookService bookService;

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public String getAuthor() {return author;}

	public void setAuthor(String author) {this.author = author;}

	public int getPrice() {return price;}

	public void setPrice(int price) {this.price = price;}

	public String getPublisher() {return publisher;}

	public void setPublisher(String publisher) {this.publisher = publisher;}

	public int getStock() {return stock;}
	
	public String getType() {return type;}
	
	public void setType(String type) {this.type = type;}

	public void setStock(int stock) {this.stock = stock;}
	
	public void setBookService(BookService bookService) {this.bookService = bookService;}
	
	@Override
	public String execute() throws Exception {

		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(date);
		Book book = new Book(title, author, price, publisher, stock, dateString, type);
		bookService.addBook(book);

		return SUCCESS;
	}

}
