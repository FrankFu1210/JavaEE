package lab01.model;  

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity(name="MemberEntity")
@Table(name="Lab01_Member_Table")
public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //使用表格底層自增間隔機制
	private Integer id;   			    // ObjectId
	private String memberId;   			// 帳號
	private String password;   			// 密碼
	private String name;       			// 姓名
	private String	phone;     			// 電話
	private java.util.Date birthday;	// 生日	
	private java.sql.Timestamp registerDate;	// 會員登錄日期
	private Double weight;				// 體重
	
	public MemberBean() {
	} 
	
	public MemberBean(String memberId, String name, String password, String phone, Date birthday,
			Timestamp registerDate, double weight) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.birthday = birthday;
		this.registerDate = registerDate;
		this.weight = weight;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	public java.sql.Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(java.sql.Timestamp registerdate) {
		this.registerDate = registerdate;
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static java.util.Date convertDate(String temp){
		java.util.Date result = new java.util.Date();
		try {
			result=sdf.parse(temp);
		} catch (ParseException e) {
			result = null ; 
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public String toString() {
		return "["+memberId+","+name+","+","+phone+","+birthday+","+weight+"]";
	}

	

}