package action.book;

import action.BaseAction;
import model.Book;
import service.BookService;

public class DeleteBookAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int id;

	private BookService bookService;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public String execute() throws Exception {

		Book book = bookService.getBookById(id);
		bookService.deleteBook(book);

		return SUCCESS;
	}

}
