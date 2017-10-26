package Service;

import java.util.ArrayList;

import DAO.BookDAO;
import DAO.DAOFactory;
import DAO.exceptions.DAOException;
import Domain.Book;
import Service.exceptions.ServiceException;

public class BookService {
	
	public boolean addBook(Book book) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();
		
		try {
			bookDAO.addBook(book);
			return true;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public boolean deleteBook(String bookName, String author, String bookType) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();
		
		try {
			return bookDAO.deleteBook(bookName,author,bookType);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public ArrayList<Book> showBooks() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();
		
		try {
			return bookDAO.showBooks();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public ArrayList<Book> searchBooks(String param, String searchingType) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();
		
		try {
			return bookDAO.searchBooks(param,searchingType);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public boolean updateBook(String id,String name, String author, String year, String type) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		BookDAO bookDAO = factory.getBookDAO();
		
		try {
			return bookDAO.updateBook(id,name,author,year,type);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
