package Controller.command.implementation;

import java.util.ArrayList;

import Service.BookService;
import Service.ServiceFactory;
import Service.exceptions.ServiceException;
import Controller.command.Command;
import Domain.Book;

public class UpdateBookCommand implements Command{
	public String execute(String request) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		String response = "";
		BookService service = serviceFactory.getBookService();
		String[] paramsArray = request.split("\\|");
		try {
			if (service.updateBook(paramsArray[1],paramsArray[2],paramsArray[3],paramsArray[4],paramsArray[5]))
				response = "OK";
			else
				response = "ID is not correct";
		} catch (ServiceException e) {
			response = e.getMessage();
		}
		
		return response;
	}
}