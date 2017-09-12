package action.book;

import java.util.List;

import action.BaseAction;
import model.Book;
import service.BookService;

public class AllBooksAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BookService bookService;

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public String execute() throws Exception {

		List<Book> books = bookService.getAllBooks("");
		session().setAttribute("books", books);

		return SUCCESS;
	}
}
