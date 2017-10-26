package Main;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.Controller;
import Domain.Book;
import Domain.User;


public class Main {

	public static void main(String[] args) {
		Controller controller = new Controller();
		boolean authFlag=false;
		User user = null;
		Scanner scn = new Scanner(System.in);
		String action = null;
		
		do{
			System.out.println("1-Registration;2-Authorization");
			action = scn.nextLine();
			if (action.equals("1")){
				if (registrateUser(scn, controller))
					action="2";
			}
			if (action.equals("2")){
				user = authorizeUser(scn, controller);
				if (user!=null)
					authFlag=true;
			}
		}while(!authFlag);
		
		if (authFlag){
			do{
				System.out.println("====CHOOSE ACTION====");
				System.out.println("0-Exit");
				System.out.println("1-Watch the lsit of books");
				System.out.println("2-Find book");
				if (user.getIsAdmin()==true){
					System.out.println("3-Delete book");
					System.out.println("4-Add book");
					System.out.println("5-Change book information");
				}
				System.out.println("=====================");
				action = scn.nextLine();
				if (action.equals("0"))
					break;
				switch (action)	{
					case "1":
						dispayBookList(scn,controller);
						break;
					case "2":
						searchBook(scn, controller);
						break;
					case "3":
						deleteBook(scn, controller);
						break;
					case "4":
						addBook(scn, controller);
						break;
					case "5":
						updateBook(scn, controller);
						break;
				}
			}while(true);
		}
	}

	public static boolean registrateUser(Scanner scn, Controller controller){
		String requestString="";
		System.out.println("====REGISTRATION====");
		System.out.println("Full name:");
		requestString = scn.nextLine()+"|";
		System.out.println("Email:");
		requestString += scn.nextLine()+"|";
		System.out.println("Login:");
		requestString += scn.nextLine()+"|";
		System.out.println("Password:");
		requestString += scn.nextLine()+"|";
		System.out.println("Want to be admin? (1-yes,2-no):");
		String role=scn.nextLine();
		if (role.equals("1"))
			requestString += "true";
		if (role.equals("2"))
			requestString += "false";
		String response = controller.doAction("user_reg|"+requestString);
		if (!response.equals("Error")){
			System.out.println("Registration completed!");
			System.out.println("====================");
			return true;
		}else{
			System.out.print(response);
			System.out.println("====================");
			return false;
		}
	}

	public static User authorizeUser(Scanner scn, Controller controller){
		User user = null;
		String requestString="";
		System.out.println("====AUTHORIZATION====");
		System.out.println("Login:");
		requestString += scn.nextLine()+"|";
		System.out.println("Password:");
		requestString += scn.nextLine();
		String response = controller.doAction("user_log|"+requestString);
		String[] paramArray = response.split(",");
		if (paramArray.length == 1)
			System.out.println(response);
		else{
			user = new User();
			user.setFullName(response.split(",")[1]);
			user.setEmail(response.split(",")[2]);
			user.setLogin(response.split(",")[3]);
			user.setPassword(response.split(",")[4]);
			if ((response.split(",")[5]).equals("adm")){
				user.setIsAdmin(true);
				System.out.println("HELLO "+user.getFullName()+" (Admin)");
			}
			else{
				user.setIsAdmin(false);
				System.out.println("HELLO "+user.getFullName());
			}
			
		}
		System.out.println("====================");
		return user;
	}

	public static void displayByPages(Scanner scn,String response){
		String action;
		int pageCounter=1;
		int booksOnPage=3;
		int endOfPage=3;
		String[] books = response.split("\\|");
		ArrayList<Book> bookList = new ArrayList<Book>();
	
		for (String bookInformation : books)
		{
			Book book = new Book();
			String[] bookAttributes = bookInformation.split(",");
			book.setBookName(bookAttributes[0]);
			book.setAuthor(bookAttributes[1]);
			book.setYear(Integer.parseInt(bookAttributes[2]));
			book.setBookType(bookAttributes[3]);
			bookList.add(book);
		}
		int pagesAmount = bookList.size()/booksOnPage;
		if (bookList.size()%booksOnPage==0)
			pagesAmount = bookList.size()/booksOnPage;
		else
			pagesAmount = bookList.size()/booksOnPage + 1;
		
		if (bookList.size()<=booksOnPage)
			endOfPage = bookList.size();
		else
			endOfPage = booksOnPage*(pageCounter-1)+booksOnPage;
		
		do{
			for (int i = (pageCounter-1)*booksOnPage; i<endOfPage;i++)
			{
				System.out.println(String.valueOf(i+1)+") "+bookList.get(i).getBookName());
				System.out.println(bookList.get(i).getAuthor());
				System.out.println(bookList.get(i).getYear());
				System.out.println(bookList.get(i).getBookType());
			}
			System.out.println("a-previous page, d-next page,q-exit");
			action = scn.nextLine();
			if (action.equals("a"))
			{
				if (pageCounter!=1)
					pageCounter--;
			}
			if (action.equals("d"))
				if (pageCounter!=pagesAmount)
					pageCounter++;
			if (action.equals("q"))
				break;
		
			if (pageCounter==pagesAmount)
				if(bookList.size() % booksOnPage!=0)
					endOfPage = booksOnPage*(pageCounter-1)+ bookList.size() % booksOnPage;
				else
					endOfPage = booksOnPage*(pageCounter-1)+booksOnPage;
			else
				endOfPage = booksOnPage*(pageCounter-1)+booksOnPage;
		}while(true);
	}
	
	public static void dispayBookList(Scanner scn, Controller controller){
		String response;
		System.out.println("====BOOK COLLECTION====");
		response = controller.doAction("show_books");
		if (response.equals(""))
			System.out.println("There are not any books");
		else
			displayByPages(scn,response);
		System.out.println("====================");
	}

	public static void searchBook(Scanner scn, Controller controller){
		String paramType=null,action,requestString="",response;
		System.out.println("====SEARCH BOOKS====");
		System.out.println("1-By the name");
		System.out.println("2-By the author");
		System.out.println("3-By the type (paper, ebook)");
		action = scn.nextLine();
		switch (action){
			case "1":
				paramType="name";
				break;
			case "2":
				paramType="author";
				break;
			case "3":
				paramType="type";
				break;
		}
		System.out.println("Input your value");
		requestString+=scn.nextLine();
		response = controller.doAction("search_books|"+requestString+"|"+paramType);
		if (response.equals(""))
			System.out.println("There are not any books");
		else
			displayByPages(scn,response);
		System.out.println("====================");
	}

	public static void deleteBook(Scanner scn, Controller controller){
		String action,requestString="",response;
		System.out.println("====DELETE BOOK====");
		System.out.println("1-Paper book");
		System.out.println("2-Electronic book");
		action = scn.nextLine();
		System.out.println("Book Name:");
		requestString = scn.nextLine()+"|";
		System.out.println("Author:");
		switch (action){
		case "1":
			requestString += scn.nextLine()+"|1";
			response = controller.doAction("del_book|"+requestString);
			if (!response.equals("OK"))
				System.out.println(response);
			else
				System.out.println("Completed!");
			break;
		case "2":
			requestString += scn.nextLine()+"|2";
			response = controller.doAction("del_book|"+requestString);
			if (!response.equals("OK"))
				System.out.println(response);
			else
				System.out.println("Completed!");
			break;
		}
		System.out.println("====================");
	}

	public static void addBook(Scanner scn, Controller controller){
		String action,requestString="",response;
		System.out.println("====ADD BOOK====");
		System.out.println("1-Paper book");
		System.out.println("2-Electronic book");
		action = scn.nextLine();
		switch (action){
		case "1":
			System.out.println("Book Name:");
			requestString = scn.nextLine()+"|";
			System.out.println("Author:");
			requestString += scn.nextLine()+"|";
			System.out.println("Year of creation:");
			requestString += scn.nextLine()+"|1";
			response = controller.doAction("add_book|"+requestString);
			if (!response.equals("OK"))
				System.out.println(response);
			else
				System.out.println("Completed!");
			break;
		case "2":
			requestString="";
			System.out.println("Book Name:");
			requestString = scn.nextLine()+"|";
			System.out.println("Author:");
			requestString += scn.nextLine()+"|";
			System.out.println("Year of creation:");
			requestString += scn.nextLine()+"|2";
			response = controller.doAction("add_book|"+requestString);
			if (!response.equals("OK"))
				System.out.println(response);
			else
				System.out.println("Completed!");
			break;
		}
		System.out.println("Do you want send letters? 1-yes, 2-no");
		action = scn.nextLine();
		if (action.equals("1")){
			String requestForSending="";
			System.out.println("Input your email");
			requestForSending = scn.nextLine()+"|";
			System.out.println("Input password from email");
			requestForSending += scn.nextLine();
			response  = controller.doAction("send_emails|"+requestForSending+"|"+requestString);
			if (response.equals("OK"))
				System.out.println("Completed!");
			else
				System.out.println("Error during sending process!");
		}
		System.out.println("====================");
	}

	public static void updateBook(Scanner scn, Controller controller){
		String requestString="",response;
		System.out.println("====UPDATE BOOK====");
		System.out.println("Input Book ID");
		String bookID = scn.nextLine();
		System.out.println("Input new book name");
		requestString += scn.nextLine()+"|";
		System.out.println("Input new book author");
		requestString += scn.nextLine()+"|";
		System.out.println("Input new year of creation");
		requestString += scn.nextLine()+"|";
		System.out.println("Input new type");
		requestString += scn.nextLine()+"|";
		response = controller.doAction("update_book|"+bookID+"|"+requestString);
		if (!response.equals("OK"))
			System.out.println(response);
		else
			System.out.println("Completed!");
		System.out.println("================");
	}
	

}
