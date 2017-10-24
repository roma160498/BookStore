package Main;

import java.util.Scanner;

import Controller.Controller;
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
			System.out.println("====CHOOSE ACTION====");
			System.out.println("1-Watch the lsit of books");
			System.out.println("2-Find book");
			if (user.getIsAdmin()==true)
			{
				System.out.println("3-Delete book");
				System.out.println("4-Add book");
				System.out.println("5-Change book information");
			}
			String action = scn.nextLine();
			switch (action)
			{
			case "1":
			case "2":
			case "3":
			case "4":
				String tempString="";
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
					tempString += scn.nextLine()+"|";
					System.out.println("Amount of pages:");
					tempString += scn.nextLine()+"|";
					System.out.println("Amount of books:");
					tempString += scn.nextLine()+"|1";
					response = controller.doAction("add_book|"+tempString);
					if (!response.equals("OK"))
						System.out.println(response);
					break;
				case "2":
					tempString="";
					System.out.println("Book Name:");
					tempString = scn.nextLine()+"|";
					System.out.println("Author:");
					tempString += scn.nextLine()+"|";
					System.out.println("Year of creation:");
					tempString += scn.nextLine()+"|";
					System.out.println("Size in MB:");
					tempString += scn.nextLine()+"|";
					System.out.println("Format:");
					tempString += scn.nextLine()+"|2";
					response = controller.doAction("add_book|"+tempString);
					if (!response.equals("OK"))
						System.out.println(response);
					break;
				}
				break;
			case "5":
			}
			
			
		}
		
		

	}

}
