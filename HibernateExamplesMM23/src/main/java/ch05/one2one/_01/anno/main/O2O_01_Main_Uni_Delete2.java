package ch05.one2one._01.anno.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2one._01.anno.model.Principal;
import ch05.one2one._01.anno.model.School;

public class O2O_01_Main_Uni_Delete2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		Principal p1 = session.get(Principal.class, 4);
		if (p1 != null) {
			Integer pid = p1.getSchool().getId();
			School s1 = session.get(School.class, pid);

			// 如果只要刪除 School物件而不要刪除相依於School物件的Principal物件，
			// 1. Principal物件找出對應的School物件，容易，因為Principal物件含有School物件參考
			// 然後得到School物件的id欄位
			// 2. 由HQL找出所有參考到這個學校的校長物件
			// 3. 設定這些校長物件的School屬性為null
			// 4. 刪除學校
			String hql = "FROM Principal p WHERE p.school.id = :id";
			List<Principal> list = session.createQuery(hql).setParameter("id", pid).getResultList();
			for (Principal p : list) {
				p.setSchool(null);
			}
			session.delete(s1);
		}
		System.out.println("--------------------------------------");
		tx.commit();
		session.close();
		System.out.println("程式結束(Done...!!)");
		factory.close();
	}
}
