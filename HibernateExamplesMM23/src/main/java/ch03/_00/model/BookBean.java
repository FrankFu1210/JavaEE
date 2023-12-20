package ch03._00.model;
  
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ch03_BookBean_Table")
public class BookBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer bookId;
	String title;
	String author;
	Double price;
	Integer stock;

	public BookBean() {
		super();
	}

	public BookBean(Integer bookId, String title, String author, Double price, Integer stock) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.stock = stock;
	}
	
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "BookBean [bookId=" + bookId + ", title=" + title + ", author=" + author + ", price=" + price
				+ ", stock=" + stock + "]";
	}
	
}
