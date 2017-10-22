package Controller.command.implementation;

import java.io.IOException;

import Service.ServiceFactory;
import Service.UserService;
import Service.exceptions.ServiceException;
import Controller.command.Command;
import Domain.User;

public class UserRegCommand implements Command{
	public String execute(String request) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		UserService service = serviceFactory.getUserService();
		String response = null;
		
		User user = new User();
		String paramArray[] = request.split("\\|");
		
		user.setEmail(paramArray[2]);
		user.setFullName(paramArray[1]);
		user.setLogin(paramArray[3]);
		user.setPassword(paramArray[4]);
		user.setIsAdmin(parseBoolean(paramArray[5]));
		
		try {
			if (service.register(user))
			response = user.toString();
		} catch (ServiceException ex) {
			
			response = "Error";
		}
		
		return response;
		
	//	return null;
	}

	private boolean parseBoolean(String string) {
		if (string.equals("true"))
			return true;
		else
			return false;
	}

	
	
}