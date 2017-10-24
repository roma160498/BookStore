package DAO;

public class DAOFactory {
	private static final DAOFactory factory = new DAOFactory();

	private final BookDAO bookDAO = new BookDAO();
	private final UserDAO userDAO = new UserDAO();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return factory;
	}
	
	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
}
