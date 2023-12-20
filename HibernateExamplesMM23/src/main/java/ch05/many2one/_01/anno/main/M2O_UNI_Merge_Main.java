package ch05.many2one._01.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.many2one._01.anno.model.EmployeeUNI;
import ch05.many2one._01.anno.model.EmployerUNI;


public class M2O_UNI_Merge_Main {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		// 當修改Employee物件時，若要同時修改Employer物件,Employee類別的@ManyToOne註釋幣必須加上 CascadeType.MERGE
		// 同時要用session的merge方法來更新物件
		// 第一組：使用原來的老闆
		EmployeeUNI empee = session.get(EmployeeUNI.class, 3);
		EmployerUNI emper = empee.getEmployer();
		// 第二組：使用新建的老闆		
		EmployeeUNI empee2 = session.get(EmployeeUNI.class, 5);
		EmployerUNI emper2 = new EmployerUNI("張曉芳-新老闆");
		empee2.setEmployer(emper2);
		System.out.println("--------------------------------------");
		tx.commit();
		session.close();
		empee.setEmployeeName("林偉明New");
		emper.setEmployerName("林芳華老闆New");
		session = factory.openSession();
		tx = session.beginTransaction();
        session.merge(empee);
        session.merge(empee2);
        System.out.println("--------------------------------------");
		tx.commit();
		session.close();
		System.out.println("程式結束(Done...!!)");
		factory.close();
	}
}
