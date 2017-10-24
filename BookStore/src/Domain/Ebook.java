package Domain;

public class Ebook extends Book{
	Integer mbSize;
	String format;
	public Integer getMbSize()
	{
		return mbSize;
	}
	public String getFormat()
	{
		return format;
	}
	public void setMbSize(Integer mbSize)
	{
		this.mbSize = mbSize;
	}
	public void setFormat(String format)
	{
		this.format = format;
	}
}
