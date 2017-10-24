package DAO;

public class DAOFactory {
	private static final DAOFactory factory = new DAOFactory();

	private final PaperBookDAO papBookDAO = new PaperBookDAO();
	private final EbookDAO ebookDAO = new EbookDAO();
	private final UserDAO userDAO = new UserDAO();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return factory;
	}

	public PaperBookDAO getPaperBookDAO() {
		return papBookDAO;
	}
	
	public EbookDAO getEbookDAO() {
		return ebookDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
}
