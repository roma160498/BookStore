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
		if (paramArray[paramArray.length-1].equals("1"))
		{
			PaperBook paperBook = new PaperBook();
			paperBook.setBookName(paramArray[1]);
			paperBook.setAuthor(paramArray[2]);
			paperBook.setYear(Integer.parseInt(paramArray[3]));
			paperBook.setPagesAmount(Integer.parseInt(paramArray[4]));
			paperBook.setAmount(Integer.parseInt(paramArray[5]));
			try{
			service.addBook(paperBook);
			return "OK";
			}catch (ServiceException ex)
			{ return ex.getMessage();
			
			}
		}
		if (paramArray[paramArray.length-1].equals("2"))
		{
			Ebook ebook = new Ebook();
			ebook.setBookName(paramArray[1]);
			ebook.setAuthor(paramArray[2]);
			ebook.setYear(Integer.parseInt(paramArray[3]));
			ebook.setMbSize(Integer.parseInt(paramArray[4]));
			ebook.setFormat(paramArray[5]);
			try{
				service.addBook(ebook);
				return "OK";
				}catch (ServiceException ex)
				{ return ex.getMessage();
				
				}
		}
		
		return response;
		
	//	return null;
	}
}
