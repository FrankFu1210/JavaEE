package ch01.ex00.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ch04_MemberExample")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(name = "account")
	String userId;
	String password;
	String name;
	String phoneNo;
	Integer experience;
	Date birthday;
	Timestamp registerTime;

	public Member(Integer id, String userId, String password, String name, String phoneNo, 
			      Integer experience, Date birthday, Timestamp registerTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.phoneNo = phoneNo;
		this.experience = experience;
		this.birthday = birthday;
		this.registerTime = registerTime;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public Member(Integer id) {
		this.id = id;
	}

	public Member() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String tel) {
		this.phoneNo = tel;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", phoneNo="
				+ phoneNo + ", experience=" + experience + ", birthday=" + birthday + ", registerTime=" + registerTime
				+ "]";
	}

}
