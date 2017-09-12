package action.book;

import java.util.List;

import action.BaseAction;
import model.Book;
import service.BookService;
import service.UserService;

public class IndexAllBooksAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BookService bookService;
	
	private UserService userService;
	
	private String key;
	

	public String getKey() {return key;}


	public void setKey(String key) {this.key = key;}

	public void setBookService(BookService bookService) {this.bookService = bookService;}
	
	public void setUserService(UserService userService) {this.userService = userService;}


	@Override
	public String execute() throws Exception {
		List<Book> books = bookService.getAllBooks(key);
		session().setAttribute("books", books);
		
		if (session().getAttribute("role") != null) {
			String role = (String)session().getAttribute("role");
			if (role.equals("buyer")) {
				int id = (Integer)session().getAttribute("id");
				String buyerName = userService.getUserById(id).getUsername();
				session().setAttribute("name", buyerName);
			}
		}

		return SUCCESS;
	}
}
