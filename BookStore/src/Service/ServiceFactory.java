package Service;


public class ServiceFactory {
	private static final ServiceFactory factory = new ServiceFactory();

	private final BookService bookService = new BookService();
	private final UserService userService = new UserService();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return factory;
	}

	public BookService getBookService() {
		return bookService;
	}

	public UserService getUserService() {
		return userService;
	}
}
