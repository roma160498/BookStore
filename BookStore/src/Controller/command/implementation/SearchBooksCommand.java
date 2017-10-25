package Controller.command.implementation;

import java.util.ArrayList;

import Service.BookService;
import Service.ServiceFactory;
import Service.exceptions.ServiceException;
import Controller.command.Command;
import Domain.Book;

public class SearchBooksCommand implements Command{
	public String execute(String request){
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		BookService service = serviceFactory.getBookService();
		String response = "";
		String paramArray[] = request.split("\\|");
		ArrayList<Book> listBooks = new ArrayList<Book>();
		try {
			listBooks = service.searchBooks(paramArray[1],paramArray[2]);
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
