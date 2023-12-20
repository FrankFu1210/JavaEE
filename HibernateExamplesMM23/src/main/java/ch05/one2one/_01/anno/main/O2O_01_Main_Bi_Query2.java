package ch05.one2one._01.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2one._01.anno.model.Principal;
import ch05.one2one._01.anno.model.School;
// 查詢: 由校長來找到任職的學校
public class O2O_01_Main_Bi_Query2 {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.println("找校長(單向一對一): ");
        Principal p = session.get(Principal.class, 1);
        if ( p != null) {
        	System.out.println(p);
        } else {
        	System.out.println("要查詢的Principal紀錄不存在");
        }
        System.out.println("----------------------------");
        System.out.println("找學校(單向一對一): ");        
        School s = session.get(School.class, 2);
        if ( s != null) {
        	System.out.println(s);
        } else {
        	System.out.println("要查詢的School紀錄不存在");
        }
		tx.commit();
		session.close();
		System.out.println("程式結束(Done...!!)");
		factory.close();
		
	}
}
