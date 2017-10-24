package Domain;
import Domain.Book;

public class PaperBook extends Book
{
	Integer pagesAmount;
	Integer amount;
	public Integer getPagesAmount()
	{
		return pagesAmount;
	}
	public Integer getAmount()
	{
		return amount;
	}
	public void setPagesAmount(Integer pagesAmount)
	{
		this.pagesAmount = pagesAmount;
	}
	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}

}
