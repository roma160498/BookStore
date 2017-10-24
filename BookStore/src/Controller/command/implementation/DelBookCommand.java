package Controller.command.implementation;

import Service.BookService;
import Service.ServiceFactory;
import Service.exceptions.ServiceException;
import Controller.command.Command;
import Domain.Book;

public class DelBookCommand implements Command {
	public String execute(String request) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		BookService service = serviceFactory.getBookService();
		String response = null;
		String paramArray[] = request.split("\\|");
		if (paramArray[paramArray.length-1].equals("1"))
		{
			try {
				if (service.deleteBook(paramArray[1],paramArray[2],"paper"))
					response = "OK";
				else
				response = "Book is not exist";
			} catch (ServiceException e) {
				response = e.getMessage();
			}
		}
		else
		{
			try {
				if(service.deleteBook(paramArray[1],paramArray[2],"ebook"))
				response = "OK";
			else
			response = "Book is not exist";
			} catch (ServiceException e) {
				response = e.getMessage();
			}
		}
		return response;
	}

}
