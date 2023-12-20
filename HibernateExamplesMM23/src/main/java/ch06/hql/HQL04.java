package ch06.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;

public class HQL04 {
	public static void main(String[] args) {
		HQL04 hq = new HQL04();
		List<Object[]> list = hq.query4("select e.name, e.salary, e.birthday FROM Employee e");
		if (list != null) {
			for (Object[] oa : list) {
				System.out.printf("%10s  %8d  %16s\n", oa[0], oa[1], oa[2]);
			}
		} else {
			System.out.println("查無資料");
		}
		HibernateUtils.close();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> query4(String hql) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<Object[]> list = null;
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
