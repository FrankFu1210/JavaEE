package ch06.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// 本類別封裝單筆出版社資料
@Entity
@Table(name="ch06_BookCompany")
public class BookCompany {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ;
	private String  name;
	private String  address;
	private String  url;
	
    public BookCompany(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public BookCompany() {
	}
	
	public Integer getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}