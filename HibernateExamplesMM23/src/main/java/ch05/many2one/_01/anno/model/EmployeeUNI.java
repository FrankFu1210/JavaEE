package ch05.many2one._01.anno.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ch05_mo1_Employee_UNI")
public class EmployeeUNI {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String employeeName;
	// 只有@OneToOne, @OneToMany 可以使用 orphanRemoval = true
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employer_id_EEIT_Unknown2" , foreignKey=@ForeignKey(name = "fkc_eee_eer"))
	private EmployerUNI employer;

	public EmployeeUNI() {
	}

	public EmployeeUNI(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public EmployerUNI getEmployer() {
		return employer;
	}

	public void setEmployer(EmployerUNI employer) {
		this.employer = employer;
	}

	@Override
	public String toString() {
		return "員工 [編號=" + id + ", 員工姓名=" + employeeName + ", 雇主姓名=" + employer.getEmployerName() + "]";
	}

}
