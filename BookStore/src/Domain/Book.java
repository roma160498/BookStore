package Domain;

public class Book {
	String bookName;
	String author;
	Integer year;
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
}
