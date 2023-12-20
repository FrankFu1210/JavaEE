package ch06.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Employee")
@Table(name="ch06_EMPLOYEE")
//@Cacheable
//@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;    // attribute
	private String name;
	private Integer salary;
	private Date birthday;
	private Integer depId;

	public Employee() {
	}

	public Employee(String name, Integer salary, Date birthday, Integer depId) {
		super();
		this.name = name;
		this.salary = salary;
		this.birthday = birthday;
		this.depId = depId;
	}
	
	public Integer getId() {   
		return id;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", birthday=" + birthday + ", depId="
				+ depId + "]";
	}
}