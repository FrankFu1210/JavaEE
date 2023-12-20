package ch05.one2many._00.anno.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="ch05_om1_Employee_UNI")
@Table(name="ch05_om1_Employee_UNI")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String employeeId;
	private String name;
//	@ManyToOne
//	private Department dept;   // 由於缺少這個屬性，所以無法由Employee找到對應的Department

	public Employee() {
	}
	
	public Employee(Integer id, String employeeId, String name) {
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
	// --------下列敘述為Optional----------------	
	// fk_Dept_id欄為外鍵，存放Department類別的主鍵，在Department類別的@JoinColumn註釋中會用
	// 到此欄位 ，如果多方沒有定義此屬性，Hibernate會代為定義對應的欄位。
//		@Column(name="fk_Dept_id")
//		private Integer deptId;
	
//		public Long getDeptId() {
//			return deptId;
//		}
//		public void setDeptId(Integer deptId) {
//			this.deptId = deptId;
//		}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeId=" + employeeId + ", name=" + name + "]";
	}
	
	
//  必須刪除下列敘述，否則程式便可由多方找回到ㄧ方而形成雙向的ㄧ對多	
//	@ManyToOne  // 多對ㄧ，多方(Employee類別)內有個儲存ㄧ方(Department類別)物件參考的實例變數
//	@JoinColumn(name="fk_Department_id", nullable=false)  
//	public Department getDept() {
//		return dept;
//	}
//
//	public void setDept(Department dept) {
//		this.dept = dept;
//	}
	
	
}
