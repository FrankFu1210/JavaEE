package ch05.one2many._04.anno.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ch05_om4_Employee_BI")
public class EmployeeBI {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String employeeId;
	private String name;
//  必須刪除下列敘述，否則程式便可由多方找回到ㄧ方而形成雙向的ㄧ對多	
	@ManyToOne  // 多對ㄧ，多方(Employee類別)內有個儲存ㄧ方(Department類別)物件參考的實例變數
	@JoinColumn(name="fk_dept_id", nullable=false , foreignKey=@ForeignKey(name = "fkc_emp2_dep2"))  
	private DepartmentBI dept;   // 由於缺少這個屬性，所以無法由Employee找到對應的Department

	public EmployeeBI() {
	}
	
	public EmployeeBI(Integer id, String employeeId, String name) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.name = name;
	}

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeId=" + employeeId + ", name=" + name + "]";
	}
	
	public DepartmentBI getDept() {
		return dept;
	}

	public void setDept(DepartmentBI dept) {
		this.dept = dept;
	}
	
}
