package ch01.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "ch01_Employee")
@Table(name = "ch01_Employee_Table")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "employee_Id", columnDefinition = "VARCHAR(10) NOT NULL")
	String employeeId;
	
	String name;
	
	Integer salary;
	
	@Column(columnDefinition = "DECIMAL(5,1)")
	Double weight;
	// @Temporal只能用在java.util.Date, @Temporal(TemporalType.DATE)表示刪除時分秒，
	// 僅保留年月日。
	@Temporal(TemporalType.DATE)  
	Date birthday;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="dept_id", foreignKey=@ForeignKey(name = "fkc_emp_dep"))
	Department dept;

	public Employee() {
	}

	public Employee(Integer id, String employeeId, String name, 
		  Integer salary, Double weight, Date birthday, Department dept) {
		this.id = id;
		this.employeeId = employeeId;
		this.name = name;
		this.salary = salary;
		this.weight = weight;
		this.birthday = birthday;
		this.dept = dept;
	}

	public Employee(Integer id) {
		this.id = id;
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

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeId=" + employeeId + ", name=" + name + ", salary=" + salary
				+ ", weight=" + weight + ", birthday=" + birthday + ", dept=" + dept + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, dept, employeeId, id, name, salary, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(dept, other.dept)
				&& Objects.equals(employeeId, other.employeeId) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(salary, other.salary)
				&& Objects.equals(weight, other.weight);
	}
}

