package ch06.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;

public class HQL03 {
	public static void main(String[] args) {
		HQL03 hq = new HQL03();
		List<String> list = hq.query3("SELECT e.name FROM Employee e");
		if (list != null) {
			for (String name : list) {
				System.out.println(name);
			}
		} else {
			System.out.println("查無資料");
		}
		HibernateUtils.close();
	}

	@SuppressWarnings("unchecked")
	public List<String> query3(String hql) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<String> list = null;
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
