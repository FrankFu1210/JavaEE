package ch05.one2one._01.anno.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2one._01.anno.model.Principal;
import ch05.one2one._01.anno.model.School;

//單向一對一: 由校長(Principal)來找出任職的學校(School)，
//只要校長類別內含有該校長任職之學校的物件參考，程式就可以由校長找到任職的學校。
//以資料庫的觀點而言就是在Principal表格內增加School表格的外鍵欄位，這樣一來，
//由Principal表格內的記錄就可以找到對應之School表格內的記錄。

//單向一對一的施行步驟:
//Step 0: 為Principal類別、School類別加上應有的註釋(@Entity, @Table, @Id, ....)
//Step 1: 由於程式的需求為『由校長(Principal)來找出任職的學校(School)』，
//因此在Principal類別內定義一個儲存School類別之物件的實例變數。
//Step 2: 並在此實例變數對應之getter前面加上@OneToOne(cascade=CascadeType.ALL)
//      同時加上 @JoinColumn(name="FK_School_id")，說明在Principal表格
//      的外鍵名稱為何(此外鍵乃用來儲存School表格之主鍵)
//完成       

public class O2O_01_Main_Uni_Insert2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		// 先新建物件，Principal與School物件各兩個
		School s1 = null;
		Principal p1 = new Principal();
		p1.setName("林靜-Uni");
		String hql = "FROM School s WHERE s.schoolName = :sname ";
		List<School> schools = session.createQuery(hql)
			   .setParameter("sname", "仁愛國中-Uni")
			   .getResultList();
		if (schools.size() > 0) {
			s1 = schools.get(0);
		} else {
			s1 = new School("仁愛國中-Uni", "新竹市東區");
		}
        p1.setSchool(s1);    // 由校長可以找到學校
        
        Principal p2 = new Principal();
		p2.setName("王小明Case3-Uni");
        School s2 = new School("三民國小Case3-Uni", "高雄市左營區");
        p2.setSchool(s2);    // 由校長可以找到學校
        
        
		System.out.println("--------------------------------------");
        session.persist(s1);   // 如果Principal類別的@OneToOne註釋加入 cascade=CascadeType.PERSIST
        session.persist(s2);   // 則儲存School物件的這兩列可以省略。
        System.out.println("--------------------------------------");
        session.persist(p1);
        session.persist(p2);

		tx.commit();
		session.close();
		System.out.println("程式結束(Done...!!)");
		factory.close();
	}
}
