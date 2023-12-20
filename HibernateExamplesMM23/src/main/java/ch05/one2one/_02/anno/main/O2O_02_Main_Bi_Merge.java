package ch05.one2one._02.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2one._02.anno.model.PrincipalBi;
import ch05.one2one._02.anno.model.SchoolBi;


public class O2O_02_Main_Bi_Merge {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		// 當修改Principal物件時，若要同時修改School物件, Pincipal類別的@OneToOne註釋幣必須加上 CascadeType.MERGE
		// 同時要用session的merge方法來更新物件
		// 當加上CascadeType.MERGE時，Hibernate會發出兩個Update敘述
		// 若沒加上CascadeType.MERGE時，Hibernate會發出一個Update敘述		
		SchoolBi s = new SchoolBi(2, "曉明國小-1559", "桃園市中壢區-1559");
		PrincipalBi  p = new PrincipalBi(2, "李小民-1559");
		p.setSchool(s);
        session.merge(p);
        System.out.println("--------------------------------------");
		tx.commit();
		session.close();
		System.out.println("程式結束(Done...!!)");
		factory.close();
	}
}
