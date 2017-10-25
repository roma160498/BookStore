package Main;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.Controller;
import Domain.Book;
import Domain.User;


public class Main {

	public static void main(String[] args) {
		Controller controller = new Controller();
		String response;
		boolean authFlag=false;
		User user = null;
		Scanner scn = new Scanner(System.in);
		String way;
		do{
		System.out.println("1-Registration;2-Authorization");
		way = scn.nextLine();
		
		if (way.equals("1"))
		{
			System.out.println("====REGISTRATION====");
			String tempString="";
			System.out.println("Full name:");
			tempString = scn.nextLine()+"|";
			System.out.println("Email:");
			tempString += scn.nextLine()+"|";
			System.out.println("Login:");
			tempString += scn.nextLine()+"|";
			System.out.println("Password:");
			tempString += scn.nextLine()+"|";
			System.out.println("Want to be admin? (1-yes,2-no):");
			String role=scn.nextLine();
			if (role.equals("1"))
				tempString += "true";
			if (role.equals("2"))
				tempString += "false";
			response = controller.doAction("user_reg|"+tempString);
			if (!response.equals("Error"))
			{
			user = new User();
			user.setFullName(response.split(",")[1]);
			user.setEmail(response.split(",")[2]);
			user.setLogin(response.split(",")[3]);
			user.setPassword(response.split(",")[4]);
			if ((response.split(",")[5]).equals("adm"))
				user.setIsAdmin(true);
			else
				user.setIsAdmin(false);
			way="2";
			}else
				System.out.print(response);
		}
		if (way.equals("2"))
		{
			System.out.println("====AUTHORIZATION====");
			String tempString="";
			System.out.println("Login:");
			tempString += scn.nextLine()+"|";
			System.out.println("Password:");
			tempString += scn.nextLine();
			response = controller.doAction("user_log|"+tempString);
			String[] paramArray = response.split(",");
			if (paramArray.length == 1)
				System.out.println(response);
			else{
				user = new User();
				user.setFullName(response.split(",")[1]);
				user.setEmail(response.split(",")[2]);
				user.setLogin(response.split(",")[3]);
				user.setPassword(response.split(",")[4]);
				if ((response.split(",")[5]).equals("adm"))
				{
					user.setIsAdmin(true);
					System.out.println("HELLO "+user.getFullName()+" (Admin)");
				}
				else
				{
					user.setIsAdmin(false);
					System.out.println("HELLO "+user.getFullName());
				}
				authFlag=true;
			}
			}
		}while(!authFlag);
		if (authFlag)
		{
			String tempString;
			do{
			System.out.println("====CHOOSE ACTION====");
			System.out.println("0-Exit");
			System.out.println("1-Watch the lsit of books");
			System.out.println("2-Find book");
			if (user.getIsAdmin()==true)
			{
				System.out.println("3-Delete book");
				System.out.println("4-Add book");
				System.out.println("5-Change book information");
			}
			System.out.println("=====================");
			String action = scn.nextLine();
			if (action.equals("0"))
				break;
			switch (action)
			{
			case "1":
				System.out.println("====BOOK COLLECTION====");
				response = controller.doAction("show_books");
				if (response.equals(""))
					System.out.println("There are not any books");
				else
				{
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
				break;
			case "2":
				System.out.println("====SEARCHING BOOKS====");
				System.out.println("1-By the name");
				System.out.println("2-By the author");
				System.out.println("3-By the type (paper, ebook)");
				action = scn.nextLine();
				String paramType=null;
				tempString="";
				switch (action)
				{
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
				tempString+=scn.nextLine();
				response = controller.doAction("search_books|"+tempString+"|"+paramType);
				if (response.equals(""))
					System.out.println("There are not any books");
				else
				{
					int bookCounter=1;
					System.out.println("====SEARCHING RESULTS====");
					String[] books = response.split("\\|");
					for (String book : books)
					{
						String[] bookAttributes = book.split(",");
						System.out.println(String.valueOf(bookCounter)+")"+bookAttributes[0]);
						System.out.println(bookAttributes[1]);
						System.out.println(bookAttributes[2]);
						System.out.println(bookAttributes[3]);
						bookCounter++;
					}
				}
				break;
			case "3":
				tempString="";
				System.out.println("1-Paper book");
				System.out.println("2-Electronic book");
				action = scn.nextLine();
				System.out.println("Book Name:");
				tempString = scn.nextLine()+"|";
				System.out.println("Author:");
				switch (action)
				{
				case "1":
					tempString += scn.nextLine()+"|1";
					response = controller.doAction("del_book|"+tempString);
					if (!response.equals("OK"))
						System.out.println(response);
					else
						System.out.println("Completed!");
					break;
				case "2":
					tempString += scn.nextLine()+"|2";
					response = controller.doAction("del_book|"+tempString);
					if (!response.equals("OK"))
						System.out.println(response);
					else
						System.out.println("Completed!");
					break;
				}
				break;
			case "4":
				tempString="";
				System.out.println("1-Paper book");
				System.out.println("2-Electronic book");
				action = scn.nextLine();
				switch (action)
				{
				case "1":
					System.out.println("Book Name:");
					tempString = scn.nextLine()+"|";
					System.out.println("Author:");
					tempString += scn.nextLine()+"|";
					System.out.println("Year of creation:");
					tempString += scn.nextLine()+"|1";
					response = controller.doAction("add_book|"+tempString);
					if (!response.equals("OK"))
						System.out.println(response);
					else
						System.out.println("Completed!");
					break;
				case "2":
					tempString="";
					System.out.println("Book Name:");
					tempString = scn.nextLine()+"|";
					System.out.println("Author:");
					tempString += scn.nextLine()+"|";
					System.out.println("Year of creation:");
					tempString += scn.nextLine()+"|2";
					response = controller.doAction("add_book|"+tempString);
					if (!response.equals("OK"))
						System.out.println(response);
					else
						System.out.println("Completed!");
					break;
				}
				break;
			case "5":
				System.out.println("====Updating====");
				System.out.println("Input Book ID");
				String bookID = scn.nextLine();
				tempString="";
				System.out.println("Input new book name");
				tempString += scn.nextLine()+"|";
				System.out.println("Input new book author");
				tempString += scn.nextLine()+"|";
				System.out.println("Input new year of creation");
				tempString += scn.nextLine()+"|";
				System.out.println("Input new type");
				tempString += scn.nextLine()+"|";
				response = controller.doAction("update_book|"+bookID+"|"+tempString);
				if (!response.equals("OK"))
					System.out.println(response);
				else
					System.out.println("Completed!");
				break;
			}
			}while(true);
			
		}
		
		

	}

}
