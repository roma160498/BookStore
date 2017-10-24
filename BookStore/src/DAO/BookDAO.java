package DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import DAO.exceptions.DAOException;
import Domain.Book;
import Domain.PaperBook;

public class BookDAO {
	
	public boolean addBook(Book book) throws DAOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt",true)))
        {
			  bw.write(book.getBookName());
			  bw.newLine();
	          bw.write(book.getAuthor());
			  bw.newLine();
	          bw.write(book.getYear().toString());
	          bw.newLine();
	          bw.write(book.getBookType());
	          bw.newLine();
	    }
	    catch(FileNotFoundException ex){
	    	throw new DAOException("File not found.", ex);
	    } catch (IOException ex) {
	    	throw new DAOException("Error during the writing", ex);
		} 
		return true;
	}
	
	public boolean deleteBook(String bookName, String author, String bookType) throws DAOException {

	boolean isExist=false;
	ArrayList<Book> listBooks = new ArrayList<Book>();
		try(BufferedReader reader = new BufferedReader(new FileReader("books.txt")))
		{
			String currentName;
			do{
				Book book = new Book();
				currentName = reader.readLine();
				if (currentName==null)
					break;
				book.setBookName(currentName);
				book.setAuthor(reader.readLine());
				book.setYear(Integer.parseInt(reader.readLine()));
				book.setBookType(reader.readLine());
				listBooks.add(book);
			}while(true);
		}
	    catch(FileNotFoundException ex){
	    	throw new DAOException("File not found.", ex);
	    } catch (IOException ex) {
	    	throw new DAOException("Error during updating", ex);
		}
		for (Book book : listBooks) {
			if (book.getBookName().equals(bookName) && book.getAuthor().equals(author) && book.getBookType().equals(bookType))
			{
				listBooks.remove(book);
				isExist = true;
				break;
			}
		}
		if (!isExist)
			return false;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("books.txt")))
        {
			for (Book book : listBooks) {
			  bw.write(book.getBookName());
			  bw.newLine();
	          bw.write(book.getAuthor());
			  bw.newLine();
	          bw.write(book.getYear().toString());
	          bw.newLine();
	          bw.write(book.getBookType());
	          bw.newLine();
			}
	    }
	    catch(FileNotFoundException ex){
	    	throw new DAOException("File not found.", ex);
	    } catch (IOException ex) {
	    	throw new DAOException("Error during updating", ex);
		} 
		return true;
	}

	public ArrayList<Book> showBooks() throws DAOException{
		ArrayList<Book> listBooks = new ArrayList<Book>();
		try(BufferedReader reader = new BufferedReader(new FileReader("books.txt")))
		{
			String currentName;
			do{
				Book book = new Book();
				currentName = reader.readLine();
				if (currentName==null)
					break;
				book.setBookName(currentName);
				book.setAuthor(reader.readLine());
				book.setYear(Integer.parseInt(reader.readLine()));
				book.setBookType(reader.readLine());
				listBooks.add(book);
			}while(true);
		}
	    catch(FileNotFoundException ex){
	    	throw new DAOException("File not found.", ex);
	    } catch (IOException ex) {
	    	throw new DAOException("Error during updating", ex);
		}
		return listBooks;
	}
}