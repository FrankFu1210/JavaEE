package ch05.one2many._01.anno.model;

import javax.persistence.*;

@Entity
@Table(name = "ch05_om2_ITEM_UNI")

public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "item_id")
	private String itemId;
	private Double unitPrice;
	private Integer quantity;

	public Item() {
	}

	public Item(String itemId, Double unitPrice, Integer quantity) {
		this.itemId = itemId;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
