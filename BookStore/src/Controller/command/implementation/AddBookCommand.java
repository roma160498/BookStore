package Controller.command.implementation;

import Service.BookService;
import Service.ServiceFactory;
import Service.UserService;
import Service.exceptions.ServiceException;
import Controller.command.Command;
import Domain.Book;
import Domain.Ebook;
import Domain.PaperBook;
import Domain.User;

public class AddBookCommand implements Command{
	public String execute(String request) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		BookService service = serviceFactory.getBookService();
		String response = null;
		String paramArray[] = request.split("\\|");
		{
			Book book = new Book();
			book.setBookName(paramArray[1]);
			book.setAuthor(paramArray[2]);
			book.setYear(Integer.parseInt(paramArray[3]));
			if (paramArray[4].equals("1"))
				book.setBookType("paper");
			else
				book.setBookType("ebook");
			try{
				service.addBook(book);
				return "OK";
			}
			catch (ServiceException ex)
			{ 
				return ex.getMessage();
			}
		}
		
		//return response;
		
	//	return null;
	}
}
