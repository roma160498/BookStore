package Domain;

public class Book {
	String bookName;
	String author;
	Integer year;
	String bookType;
	
	public String getBookName()
	{
		return bookName;
	}
	public String getAuthor()
	{
		return author;
	}
	public Integer getYear()
	{
		return year;
	}
	public String getBookType()
	{
		return bookType;
	}
	public void setBookName(String bookName)
	{
		this.bookName = bookName;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public void setYear(Integer year)
	{
		this.year = year;
	}
	public void setBookType(String bookType)
	{
		this.bookType = bookType;
	}
}
