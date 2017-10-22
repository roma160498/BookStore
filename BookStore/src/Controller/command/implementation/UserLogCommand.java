package Controller.command.implementation;

import java.io.IOException;

import Service.ServiceFactory;
import Service.UserService;
import Service.exceptions.ServiceException;
import Controller.command.Command;
import Domain.User;

public class UserLogCommand implements Command{
	public String execute(String request) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		UserService service = serviceFactory.getUserService();
		String response = null;
		
		try {
			User user = service.authorize(request.split("\\|")[1],request.split("\\|")[2]);
			response = user.toString();
		} catch (ServiceException e) {
			
			response = e.getMessage();
		}
		
		return response;
		
	//	return null;
	}
}
