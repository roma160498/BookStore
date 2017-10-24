package DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import DAO.exceptions.DAOException;
import Domain.Ebook;
import Domain.PaperBook;
import Domain.User;

public class PaperBookDAO {
	
	public ArrayList<PaperBook> showBooks() throws DAOException {
		try(BufferedReader reader = new BufferedReader(new FileReader("papersBook.txt")))
		{	ArrayList<PaperBook> list = new ArrayList<PaperBook>();
			PaperBook book = new PaperBook();
			String name="";
			do{
				name=reader.readLine();
				if (name==null)
					break;
				book.setBookName(name);
				book.setAuthor(reader.readLine());
				book.setYear(Integer.parseInt(reader.readLine()));
				book.setPagesAmount(Integer.parseInt(reader.readLine()));
				book.setAmount(Integer.parseInt(reader.readLine()));
				list.add(book);
			}while(true);
			return list;
		}
	    catch(FileNotFoundException ex){
	    	throw new DAOException("File not found.", ex);
	    } catch (IOException ex) {
	    	throw new DAOException("Error during the writing", ex);
		}
	}
	
	public boolean addBook(PaperBook book) throws DAOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("papersBook.txt",true)))
        {
			  bw.write(book.getBookName());
			  bw.newLine();
	          bw.write(book.getAuthor());
			  bw.newLine();
	          bw.write(book.getYear().toString());
	          bw.newLine();
	          bw.write(book.getPagesAmount().toString());
	          bw.newLine();
	          bw.write(book.getAmount().toString());
	          bw.newLine();

	    }
	    catch(FileNotFoundException ex){
	    	throw new DAOException("File not found.", ex);
	    } catch (IOException ex) {
	    	throw new DAOException("Error during the writing", ex);
		} 
		return true;
	}
}
