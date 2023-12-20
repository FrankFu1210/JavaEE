package ch05.one2one._02.anno.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ch05_oo2_School_Table_02")
public class SchoolBi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sid;
	private String schoolName;
	private String address;
	
	@OneToOne(mappedBy = "school_p")
	PrincipalBi principal;
	
	public SchoolBi() {
	}
	
	public SchoolBi(Integer id, String schoolName, String address) {
		this.sid = id;
		this.schoolName = schoolName;
		this.address = address;
	}
	
	public Integer getId() {
		return sid;
	}

	public void setId(Integer id) {
		this.sid = id;
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

	public PrincipalBi getPrincipal() {
		return principal;
	}
	public void setPrincipal(PrincipalBi principal) {
		this.principal = principal;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String toString(){
    	return "學校: " + getSchoolName() + ", 校長:" + getPrincipal().getName() +  
    			", 地址:" + getAddress();
    }	
}
