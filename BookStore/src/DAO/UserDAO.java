package DAO;

import DAO.exceptions.DAOException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



import java.util.ArrayList;

import Domain.User;

public class UserDAO {
	public boolean register(User user) throws DAOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt",true)))
        {
			  bw.write(user.getFullName());
			  bw.newLine();
	          bw.write(user.getEmail());
			  bw.newLine();
	          bw.write(user.getLogin());
	          bw.newLine();
	          bw.write(user.getPassword());
	          bw.newLine();
	          if (user.getIsAdmin())
	        	  bw.write(("adm"));
	          else
	        	  bw.write(("usr"));
	          bw.newLine();
	    }
	    catch(FileNotFoundException ex){
	    	throw new DAOException("File not found.", ex);
	    } catch (IOException ex) {
	    	throw new DAOException("File not found.", ex);
		} 
		return true;
	}

	public User authirize(String login, String password) throws DAOException {
		User user = new User();
		boolean isExist=false;
		try(BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
		{
			String name,role;
			do{
				name =reader.readLine();
				if (name==null)
					break;
				user.setFullName(name);
				user.setEmail(reader.readLine());
				user.setLogin(reader.readLine());
				user.setPassword(reader.readLine());
				role = reader.readLine();
				if(role.equals("adm"))
					user.setIsAdmin(true);
				if(role.equals("usr"))
					user.setIsAdmin(false);
				if (user.getLogin().equals(login) && user.getPassword().equals(password)){
					isExist=true;
					break;
				}
			}while (true);
		}
		catch (IOException ex) {
			throw new DAOException("Opening file exception", ex);
		}
		if (isExist)
			return user;
		else
			return null;
	}
	
	public ArrayList<String> getEmails() throws DAOException {
		ArrayList<String> emailsList = new ArrayList<String>();
		try(BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
		{
			
			String name;
			do{
				name = reader.readLine();
				if (name==null)
					break;
				emailsList.add(reader.readLine());
				reader.readLine();
				reader.readLine();
				reader.readLine();
			}while (true);
		}
		catch (IOException ex) {
			throw new DAOException("Opening file exception", ex);
		}
		return emailsList;
	}
}
