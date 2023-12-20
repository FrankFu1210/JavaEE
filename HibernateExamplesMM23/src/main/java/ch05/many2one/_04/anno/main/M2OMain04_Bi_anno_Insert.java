package ch05.many2one._04.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.many2one._04.anno.model.AddressBi;
import ch05.many2one._04.anno.model.PersonBi;


//一個戶籍地址(AddressBi)可以包含許多人(PersonBi)，而每個人只有一個戶籍地址。
//雙向多對ㄧ: 
//可由PersonBi(多方)找到AddressBi(ㄧ方)，因此PersonBi必須定義一個能儲存
//AddressBi之物件參考的實例變數。
//
//	private AddressBi addressBi;  
//
//亦可由AddressBi(ㄧ方)找到PersonBi(多方)，因此AddressBi必須定義一個能儲存
//多個PersonBi物件的 Set<PersonBi>型別的實例變數。 
//
//  Set<PersonBi> set = new HashSet<>();
//
// 
//於PersonBi類別中，private AddressBi addressBi; 實例變數對應的getter()方法前
//加上
//	@ManyToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="fk_address_id")
//	public AddressBi getAddressBi() {
//		return addressBi;
//	}
//於AddressBi類別中，Set<PersonBi> set = new HashSet<>(); 實例變數對應的getter()方法前
//加上
//	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
//	public Set<PersonBi> getSet() {
//		return set;
//	}
public class M2OMain04_Bi_anno_Insert {
	public static void main(String[] args) {
        AddressBi ad1 = new AddressBi("台北市松山區松山路101號");
        AddressBi ad2 = new AddressBi("台北市士林區中山北路七段135號");
       
        PersonBi p1 = new PersonBi("張君雅(松山區)");
        p1.setAddressBi(ad1);
        PersonBi p2 = new PersonBi("劉麗芳(松山區)");
        p2.setAddressBi(ad1);

        PersonBi p3 = new PersonBi("徐衛國(士林區)");
        p3.setAddressBi(ad2);
        PersonBi p4 = new PersonBi("林曉芳(士林區)");
        p4.setAddressBi(ad2);
        
        SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try{
//			session.save(ad1);   // 如果有加Cascade時可省略
			session.save(p1);
			session.save(p2);
			System.out.println("----------------");
			session.save(p3);
			session.save(p4);
			tx.commit();
		} catch(Exception e){
			System.out.println(e);
			if (tx != null) 
				tx.rollback();
		} finally{
			if (session != null)
			session.close();
		}
		HibernateUtils.close();
	}
}
