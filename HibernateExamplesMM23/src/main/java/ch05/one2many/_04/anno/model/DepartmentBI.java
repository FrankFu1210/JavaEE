package ch05.one2many._04.anno.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ch05_om4_Department_BI")
public class DepartmentBI {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dept_id")
	private Integer id;
	private String deptCode;
	private String deptName;
	// 如果省略cascade={CascadeType.ALL}，Hibernate不會代為儲存多筆Employee物件，必須程式自行儲存
	@OneToMany(mappedBy = "dept", fetch=FetchType.EAGER, 
			        cascade = { CascadeType.PERSIST }, orphanRemoval = false
			  )
	// mappedBy="dept" 說明: 本類別沒有外鍵資訊，外鍵資訊在對照類別的dept性質所對應的getter方法中有詳細說明。
	// Employee類別中，用 @ManyToOne修飾的性質(或屬性)名稱，多方必須定義外鍵(@JoinColumn)
	// 加上mappedBy屬性后，本類別就不是Owner，不負責維繫關聯關係
	// 如果省略mappedBy屬性，Hibernate會採用JoinTable來連結兩個表格
	private Set<EmployeeBI> employees = new LinkedHashSet<>();

	//
	public DepartmentBI(Integer id, String deptCode, String deptName, Set<EmployeeBI> employees) {
		this.id = id;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.employees = employees;
	}

	public DepartmentBI() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<EmployeeBI> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeBI> employees) {
		this.employees = employees;
	}

}
