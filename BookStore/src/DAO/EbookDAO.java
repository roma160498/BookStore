package DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DAO.exceptions.DAOException;
import Domain.Ebook;

public class EbookDAO{
	public boolean addBook(Ebook book) throws DAOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("electronicBook.txt",true)))
        {
			  bw.write(book.getBookName());
			  bw.newLine();
	          bw.write(book.getAuthor());
			  bw.newLine();
	          bw.write(book.getYear().toString());
	          bw.newLine();
	          bw.write(book.getMbSize());
	          bw.newLine();
	          bw.write(book.getFormat());
	          bw.newLine();
	          bw.newLine();

	    }
	    catch(FileNotFoundException ex){
	    	throw new DAOException("File not found.", ex);
	    } catch (IOException ex) {
	    	throw new DAOException("Error during the writing", ex);
		} 
		return true;
	}
	
	public Integer findBook(Ebook book) throws DAOException{
		Integer index=-1;
		try(BufferedReader reader = new BufferedReader(new FileReader("electronicBook.txt")))
		{
			index++;
			do{
				String name = reader.readLine();
				if (name==null)
					throw new DAOException("Book is not exist");
				if (name.equals(book.getBookName()))
				{
					return index;
				}
				reader.readLine();
				reader.readLine();
				reader.readLine();
				reader.readLine();
			}while(true);
		}
		catch (IOException ex) {
			throw new DAOException("Opening file exception", ex);
		}
		
	}
	
/*	public boolean delBook(Ebook book) throws DAOException {
		
		boolean isExist=false;
		try(BufferedReader reader = new BufferedReader(new FileReader("electronicBook.txt")))
		{
			try(BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt",true)))
	        {
				  
				do{
					String name = reader.readLine();
					if (name==null && !isExist)
						throw new DAOException("Book is not exist");
					if (name.equals(book.getBookName()))
					{
						isExist=true;
						reader.readLine();
						reader.readLine();
						reader.readLine();
						reader.readLine();
					}
					bw.write(name);
					bw.newLine();
			        bw.write(reader.readLine());
					bw.newLine();
			        bw.write(reader.readLine());
			        bw.newLine();
			        bw.write(reader.readLine());
			        bw.newLine();
			        bw.write(reader.readLine());
			        bw.newLine();
					
				}while(true);
	        }
		}
	    catch(FileNotFoundException ex){
	    	throw new DAOException("File not found.", ex);
	    } catch (IOException ex) {
	    	throw new DAOException("Error during the writing", ex);
		}
		return isExist;
	}
*/	
public ArrayList<Ebook> showBooks() throws DAOException {
		try(BufferedReader reader = new BufferedReader(new FileReader("electronicBook.txt")))
		{	ArrayList<Ebook> list = new ArrayList<Ebook>();
			Ebook book = new Ebook();
			String name="";
			do{
				name=reader.readLine();
				if (name==null)
					break;
				book.setBookName(name);
				book.setAuthor(reader.readLine());
				book.setYear(Integer.parseInt(reader.readLine()));
				book.setMbSize(Integer.parseInt(reader.readLine()));
				book.setFormat(reader.readLine());
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
		
}
