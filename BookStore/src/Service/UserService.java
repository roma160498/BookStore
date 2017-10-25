package Service;

import java.io.IOException;
import java.util.ArrayList;

import DAO.DAOFactory;
import DAO.UserDAO;
import DAO.exceptions.DAOException;
import Domain.User;
import Service.exceptions.ServiceException;

public class UserService {

	public boolean register(User user) throws ServiceException{
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		try {
			userDAO.register(user);
		} catch (DAOException ex) {
			throw new ServiceException("File not found.", ex);
		}
		return true;
	}

	public User authorize(String login, String password) throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		User user;
		try {
			user = userDAO.authirize(login,password);
		} catch (DAOException ex) {
			throw new ServiceException(ex.getMessage(), ex);
		} 
		return user;
	}
	
	public ArrayList<String> getEmails() throws ServiceException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		ArrayList<String> emailsList;
		try {
			emailsList = userDAO.getEmails();
		} catch (DAOException ex) {
			throw new ServiceException(ex.getMessage(), ex);
		} 
		return emailsList;
	}
}
