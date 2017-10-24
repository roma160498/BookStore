package DAO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import DAO.exceptions.DAOException;
import Domain.PaperBook;
import Domain.User;

public class PaperBookDAO {
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
