package ch05.one2one._01.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2one._01.anno.model.Principal;


public class O2O_01_Main_Uni_Delete {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		Principal p1 = session.get(Principal.class, 1);
//		School s1 = session.get(School.class, 2);
		// 如果只刪除 School物件而未刪除相依於School物件的Principal物件，		
		// 會得到下列訊息: ERROR: Cannot delete or update a parent row: a foreign key constraint fails (`jspdb`.`principal_table`, CONSTRAINT `FK2n8kojxx5kxetai3y5bx16ois` FOREIGN KEY (`FK_School_id`) REFERENCES `school_table` (`id`))
		// session.delete(s1);  // NG
    		
		// 如果只刪除 Principal物件，則視Principle類別的@OneToOne中，cascade屬性的寫法：
		// 如果寫成 @OneToOne(cascade= { CascadeType.REMOVE})，則由於由於校長表格含有外鍵，
		// 會連帶刪除學校。
		// 如果沒有編寫cascade= { CascadeType.REMOVE})，則只會刪除校長物件對應的紀錄。
        session.remove(p1);     
        System.out.println("--------------------------------------");
		tx.commit();
		session.close();
		System.out.println("程式結束(Done...!!)");
		factory.close();
	}
}
