package ch05.one2one._02.anno.main;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2one._02.anno.model.PrincipalBi;
import ch05.one2one._02.anno.model.SchoolBi;
//雙向一對一 : 由校長(Principal)與學校(School)彼此都可找到對方
public class O2O_02_Main_Bi_Insert {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		PrincipalBi p1 = new PrincipalBi(null, "劉梅芳Bi");
        SchoolBi s1 = new SchoolBi(null, "梅芳高中Bi", "台北市松山區");
        p1.setSchool(s1);
        s1.setPrincipal(p1);
        PrincipalBi p2 = new PrincipalBi(null, "曾文山Bi");
		SchoolBi s2 = new SchoolBi(null, "文山國中Bi", "台北市文山區");
        p2.setSchool(s2);
        s2.setPrincipal(p2);
		Transaction tx = session.beginTransaction();
//        session.save(s1);
//        session.save(s2);		
//        session.save(p1);
//        session.save(p2);
        session.persist(p1);
        session.persist(p2);
		tx.commit();
		session.close();
		System.out.println("程式結束(Done...!!)");
		factory.close();
	}
}
