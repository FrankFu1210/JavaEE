package ch06.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch06.model.Employee;

public class HQL13 {
	public static void main(String[] args) {
		HQL13 hq = new HQL13();
		List<Employee> list = hq.query13("FROM Employee ORDER BY id", 0, 3);
		if (list != null) {
			for (Employee e : list) {
				System.out.printf("%2d %6s %6d %24s %2d\n", e.getId(), e.getName(), e.getSalary(), e.getBirthday(),
						e.getDepId());
			}
		} else {
			System.out.println("查無資料");
		}
		HibernateUtils.close();
	}
	@SuppressWarnings("unchecked")
	public List<Employee> query13(String hql, int s, int len) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Employee> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = session.createQuery(hql)
						  .setFirstResult(s)
						  .setMaxResults(len)
						  .getResultList();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return list;
	}
	
}
