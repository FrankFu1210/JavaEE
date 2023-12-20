package ch06.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;

public class HQL03A {
	public static void main(String[] args) {
		HQL03A hq = new HQL03A();
		List<Integer> list = hq.query3("SELECT e.salary FROM Employee e");
		if (list != null) {
			for (Integer salary : list) {
				System.out.println(salary);
			}
		} else {
			System.out.println("查無資料");
		}
		HibernateUtils.close();
	}

	@SuppressWarnings("unchecked")
	public List<Integer> query3(String hql) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<Integer> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = session.createQuery(hql).getResultList();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return list;
	}

}
