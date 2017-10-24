package Controller.command.implementation;

import java.util.ArrayList;

import Service.BookService;
import Service.ServiceFactory;
import Service.exceptions.ServiceException;
import Controller.command.Command;
import Domain.Book;

public class ShowBooksCommand implements Command {

	@Override
	public String execute(String request) {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		String response = "";
		BookService service = serviceFactory.getBookService();
		
		ArrayList<Book> listBooks = new ArrayList<Book>();
		try {
			listBooks = service.showBooks();
			for(Book book : listBooks)
			{
				response += book.getBookName()+","+book.getAuthor()+","+String.valueOf(book.getYear())+","+book.getBookType()+"|";
			}
		} catch (ServiceException e) {
			response = e.getMessage();
		}
		
		return response;
	}

}
