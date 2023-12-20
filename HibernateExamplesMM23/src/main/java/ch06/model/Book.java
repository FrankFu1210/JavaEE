package ch06.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// 本類別封裝單筆書籍資料
@Entity
@Table(name = "ch06_Book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	private String title;
	private String author;
	private double price;
	private double discount;
	private Integer companyId;
	private String companyName;
	private String fileName;
	private String bookNo;
	private Blob coverImage;

	public Blob getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(Blob coverImage) {
		this.coverImage = coverImage;
	}

	private String discountStr;

	public Book(Integer bookId, String title, String author, double price, double discount, Integer companyId,
			String fileName, String bookNo) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.discount = discount;
		this.companyId = companyId;
		this.fileName = fileName;
		this.bookNo = bookNo;
	}

	public Book() {
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookID) {
		this.bookId = bookID;
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

	private String priceStr = null;

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
		if (priceStr == null) {
			priceStr = String.valueOf(price);
		}
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
		if (discount == 1) {
			discountStr = "";
		} else {
			Integer dnt = (int) (discount * 100);
			if (dnt % 10 == 0) {
				discountStr = (dnt / 10) + "折";
			} else {
				discountStr = " " + dnt + "折";
			}

		}
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDiscountStr() {
		return discountStr;
	}

	public void setDiscountStr(String discountStr) {
		discountStr = this.discountStr;
	}

	public String toString() {
		return "bookId=" + bookId + "  title=" + title + "  author=" + author + "   price=" + price + "   discount="
				+ discount + "  companyId=" + companyId;
	}
}