package ch05.one2one._02.anno.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
// 雙向一對一，可由PrincipalBi物件與SchoolBi物件都可以查找對方。
// 關注標籤: @OneToOne
@Entity
@Table(name = "ch05_oo2_Principal_Table_02")
public class PrincipalBi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	private String name;
	
	@OneToOne(cascade =CascadeType.PERSIST)
	// If there is a single join column, and if the name annotation member is 
	// missing, the join column name is formed as the concatenation of the 
	// following: the name of the referencing relationship property or field 
	// of the referencing entity; "_"; the name of the referenced primary key 
	// column. If there is no such referencing relationship property or field 
	// in the entity (i.e., a join table is used), the join column name is 
	// formed as the concatenation of the following: the name of the entity; "_"; the name of the referenced primary key column.
	// Source==> https://stackoverflow.com/questions/3964059/jpa-default-column-name-mapping-for-manytoone-relations
	@JoinColumn(name="school_id", foreignKey=@ForeignKey(name = "fkc_pri_sch2"))
	private SchoolBi school_p;

	public PrincipalBi() {
	}

	public PrincipalBi(Integer id, String name) {
		this.pid = id;
		this.name = name;
	}

	public Integer getId() {
		return pid;
	}

	public void setId(Integer id) {
		this.pid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SchoolBi getSchool() {
		return school_p;
	}

	public void setSchool(SchoolBi school) {
		this.school_p = school;
	}

	public String toString() {
		return "校長: " + name + ", 服務學校:" + school_p.getSchoolName() + ", 地址:" + school_p.getAddress();
	}
}
