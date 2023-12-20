package ch05.one2one._02.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2one._02.anno.model.PrincipalBi;
import ch05.one2one._02.anno.model.SchoolBi;

public class O2O_02_Main_Bi_Query {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("查找校長:");
        PrincipalBi p = session.get(PrincipalBi.class, 1);
        if ( p != null) {
        	System.out.println("校長:" + p);
        } else {
        	System.out.println("查無此筆資料");
        }
        System.out.println("---------------------");
        System.out.println("查找學校:");
        SchoolBi s = session.get(SchoolBi.class, 2);
        if ( s != null) {
        	System.out.println("學校:" + s);
        } else {
        	System.out.println("查無此筆資料");
        }
		tx.commit();
		session.close();
		System.out.println("程式結束(Done...!!)");
		factory.close();
	}
}
