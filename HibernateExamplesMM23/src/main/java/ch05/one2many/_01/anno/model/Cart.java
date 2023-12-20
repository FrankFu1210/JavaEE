package ch05.one2many._01.anno.model;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="ch05_om2_CART_UNI")
public class Cart {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;
    private Double total;
    private String name; 
    // CascadeType.REMOVE,
    @OneToMany(cascade= { CascadeType.PERSIST})
    // 說明多方表格的fk_cartid欄位為外鍵欄位，對照的主鍵為一方表格的cart_id欄
    @JoinColumn(name="fk_cartid", referencedColumnName="cart_id", 
                      nullable = true, foreignKey=@ForeignKey(name = "fkc_ite_cat"))
    // nullable = false: 說明外鍵欄位不允許 null, 預設值為true
    private Set<Item> item = new LinkedHashSet<>();
    
    public Cart() {
	}
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   
    public Set<Item> getItemsAnno() {
        return item;
    }
    public void setItemsAnno(Set<Item> item) {
        this.item = item;
    }    
}

