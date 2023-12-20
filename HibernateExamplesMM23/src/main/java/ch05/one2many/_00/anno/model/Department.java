package ch05.one2many._00.anno.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "ch05_om1_Department_UNI")
@Table(name = "ch05_om1_Department_UNI")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dept_id")
	private Integer id;
	private String deptCode;
	private String deptName;
	// 如果省略cascade={CascadeType.ALL}，Hibernate不會代為儲存多筆Employee物件，必須程式自行儲存
	// orphanRemoval只能用在@OneToMany與@OneToOne上
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	// @JoinColumn說明多方表格的fk_dept_id欄位為外鍵欄位(Employee類別對應的表格有fk_dept_id)，對照的主鍵為一方表格的dept_id欄
	// Employee(多方)類別中可以定義，也可以不定義對應外鍵欄位的屬性
	@JoinColumn(name = "fk_dept_id", referencedColumnName = "dept_id")
	private Set<Employee> employees = new LinkedHashSet<>();

	//
	public Department(Integer id, String deptCode, String deptName, Set<Employee> employees) {
		this.id = id;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.employees = employees;
	}

	public Department() {
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

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
