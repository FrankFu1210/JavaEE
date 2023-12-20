package ch05.one2one._01.anno.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ch05_oo1_School_Table")
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String schoolName;
	private String address;
	// 由於本例為單向的ㄧ對ㄧ，而School類別不是Relationship Owner, 所以School類別不需
	// 加上額外的註釋。
	// 如果增加如下一行所示的 Principal型態的實例變數與對應的Getter/Setter，並在
	// 變數principal之前加上@OneToOne(mappedBy="school")
	// 就是雙向的一對一。
//	 @OneToOne(mappedBy="school")  // getSchool()
//	 private Principal principal;
	// 由於 School類別內沒有 Principal 型態的實例變數，因此無法由School物件找到
	// Principal物件。

	public School() {
		super();
	}

	public School(Integer id, String schoolName, String address) {
		super();
		this.id = id;
		this.schoolName = schoolName;
		this.address = address;
	}

	public School(String schoolName, String address) {
		super();
		this.schoolName = schoolName;
		this.address = address;
	}
	// @OneToOne: 說明School到Prinpal也是一對一的關係。
	// 屬性mappedBy="school": 由於本類別並未使用@JoinColumn，所以本類別對應之
	// 表格沒有表示關係的外鍵。因此mappedBy屬性的作用為通知Hibernate，
	// 『到對照類別(Principal)內的school性質去找，該性質有說明兩表格關聯的資訊
	//
	// 注意：表格之間的關係僅一方需要有外鍵即可，如果打開下列註解，兩照類別的關係將變成雙向
//	 public Principal getPrincipal() {
//		 return principal;
//	 }
//	
//	 public void setPrincipal(Principal principal) {
//		 this.principal = principal;
//	 }

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return "學校: " + getSchoolName() + /* ", 校長:" + getPrincipal().getName() + */  
				", 地址:" + getAddress();
	}
}
