package Domain;

public class Book {
	String bookName;
	String author;
	int year;
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
	@Override	
	public String toString()
	{
		return getClass()+","+bookName+","+author+","+String.valueOf(year)+","+bookType;
	}
	@Override
	public boolean equals(Object obj) { 
        if (obj == this) { 
                return true; 
        } 
        if (obj == null || obj.getClass() != this.getClass()) { 
                return false; 
        } 

        Book book = (Book) obj; 
        return (year == book.getYear() 
                && (bookName == book.bookName || (bookName != null &&bookName.equals(book.getBookName())))                
                && (author == book.author  || (author != null && author .equals(book.getAuthor())))
                && (bookType == book.bookType  || (bookType != null && bookType .equals(book.getBookType())))); 
    } 
}
