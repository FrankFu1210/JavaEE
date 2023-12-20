package ch05.many2one._04.anno.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ch05_mo2_AddressBi_Table")
public class AddressBi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	private String name;
	// mappedBy: 說明本類別並未含有能夠表示關聯的資訊，此項資訊位於PersonBi類別的
	// address性質中
	@OneToMany(mappedBy = "addressBi", cascade = CascadeType.ALL)
	Set<PersonBi> set = new HashSet<>();

	public AddressBi() {
	}

	public AddressBi(String name) {
		this.name = name;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PersonBi> getSet() {
		return set;
	}

	public void setSet(Set<PersonBi> set) {
		this.set = set;
	}

	public String toString() {
		String msg = "地址: " + getName() + " 居住人員";
		for (PersonBi p : set) {
			msg += "編號:" + p.getPersonId() + ", 姓名: " + p.getName() + "\n";
		}
		return msg;
	}
}
