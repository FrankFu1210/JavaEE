package ch06.hql;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch06.model.Employee;

public class HQL09 {
	public static void main(String[] args) {
		HQL09 hq = new HQL09();
		List<Employee> list = hq.query9();
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

	// Using Positional Paramters，Hibernate 5 已經不再支援
	@SuppressWarnings("unchecked")
	public List<Employee> query9() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		String hql = "FROM Employee e WHERE e.salary > ? and " 
					+ "birthday > ? order by salary desc";

		List<Employee> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Date d = Date.valueOf("1979-12-01");
			list = session.createQuery(hql)
						  .setParameter(0, 25000)
						  .setParameter(1, d)
						  .getResultList();
			tx.commit();
		} catch (IllegalArgumentException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("發生例外：Legacy-style query parameters (`?`) are no longer supported");
	
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return list;
	}
}
