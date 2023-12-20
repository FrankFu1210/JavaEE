package ch05.one2one._01.anno.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
// 單向一對一，可由Principal物件找到School物件，但無法由反向查找。
// 關注標籤: @OneToOne， @JoinColumn
@Entity
@Table(name="ch05_oo1_Principal_Table")
public class Principal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;
	@OneToOne(cascade= {CascadeType.PERSIST })
	@JoinColumn(name="FK_School_id", foreignKey=@ForeignKey(name = "fkc_pri_sch"))
	private School school;
	
	public Principal() {
	}
	
	public Principal(Integer id, String name, School school) {
		this.id = id;
		this.name = name;
		this.school = school;
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
	// cascade=CascadeType.PERSIST的作用：當儲存的物件含有參考之物件時，
	// Hibernate會先寫入參考之物件，然後寫入此物件。
	// 以本例而言，Principal表格含有School表格的外鍵，所以只要Principal物件含有合法的
	// School物件參考，儲存Principal物件時，會先寫入School物件，然後才寫入Principal物件。
	// 
	// 如果省略 cascade=CascadeType.PERSIST時，程式替必須先儲存School物件，然後儲存Principal物件
	// 否則會得到一個錯誤訊息: 
	// ERROR: HHH000346: Error during managed flush [org.hibernate.TransientObjectException: 
	// object references an unsaved transient instance - save the transient instance before flushing: 
	// ch05.one2one._01.anno.model.School]
	// 例如: 下面的敘述可以在省略 cascade=CascadeType.PERSIST時，正確的將School物件與
	// Principal物件寫入各自的表格內。
	// 
//	System.out.println("--------------------------------------");
//  session.persist(s1);   // 
//  session.persist(p1);
//  System.out.println("--------------------------------------");
//  session.persist(s2);  
//  session.persist(p2);
//	
	//儲存
//	@OneToOne(cascade= {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST  })
//	@OneToOne(cascade= {CascadeType.REMOVE, CascadeType.MERGE })
	// 1. @JoinColumn(name="FK_School_id")->說明外鍵的欄位為何
	// 在本類別(Principal)所對應的表格(Principal_Table)內加入能找到School紀錄之外鍵，
	// 外鍵名稱為『FK_School_id』
	// 2. 如果改為 @JoinColumn(name="id")
	// 利用本類別(Principal)的主鍵當作是外來鍵，因此 Principal表格就不會多
	// 出一個外鍵
	// 3. 加入@JoinColumn註釋之類別表示可由此類別的物件找到對照之類別的物件(們)	
//	@JoinColumn(name="FK_School_id")
	//@JoinColumn(name="id")
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
	
	
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString(){
    	return "校長: " + name + ", 服務學校:" + school.getSchoolName() + 
    			", 地址:" + school.getAddress();
    }	
}
