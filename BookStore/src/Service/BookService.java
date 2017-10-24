package Service;

import DAO.DAOFactory;
import DAO.EbookDAO;
import DAO.PaperBookDAO;
import DAO.exceptions.DAOException;
import Domain.Ebook;
import Domain.PaperBook;
import Service.exceptions.ServiceException;

public class BookService {
	
	public boolean addBook(PaperBook book) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		PaperBookDAO bookDAO = factory.getPaperBookDAO();
		
		try {
			bookDAO.addBook(book);
			return true;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public boolean addBook(Ebook book) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		EbookDAO bookDAO = factory.getEbookDAO();
		
		try {
			bookDAO.addBook(book);
			return true;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
