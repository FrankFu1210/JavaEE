package ch05.many2one._04.anno.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ch05_mo2_PersonBi_Table")
public class PersonBi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer personId;
	private String name;
	// 加上@ManyToOne註釋的目的:
	// 1. 讓Hibernate知道address是表示類別之間的Association而非ㄧ個欄位
	// 2. 讓Hibernate在Person_Table中建立ㄧ個做為外來鍵的欄位，欄位名稱可以是自定(透過@JoinColumn)
	// 或由Hibernate依照預設規則來決定欄位名稱(外來鍵之來源表格名稱 + "_" + 來源表格的主鍵名稱)
	@ManyToOne(cascade = CascadeType.ALL)
	// 加上@JoinColumn註釋的目的: 指定Person_Table中，外來鍵的欄位名稱
	@JoinColumn(name = "fk_address_id")
	private AddressBi addressBi;

	public PersonBi() {
	}

	public PersonBi(String name) {
		this.name = name;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressBi getAddressBi() {
		return addressBi;
	}

	public void setAddressBi(AddressBi addressBi) {
		this.addressBi = addressBi;
	}

	public String toString() {
		return "編號:" + personId + ", 姓名: " + name + ", 地址: " + addressBi.getName();
	}
}
