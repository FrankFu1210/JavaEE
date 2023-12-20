package ch06.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch06.model.Employee;

public class HQL06 {
	public static void main(String[] args) {
		HQL06 hq = new HQL06();
		List<Employee> list = hq.query6("FROM Employee e WHERE e.salary >= 30000 " 
								+ " ORDER BY e.salary DESC, e.depId ASC");
		if (list != null) {
			for (Employee e : list) {
				System.out.printf("%3d %10s %8d %14s %2d\n", e.getId(), e.getName(), 
						e.getSalary(), e.getBirthday(),	e.getDepId());
			}
		} else {
			System.out.println("查無資料");
		}

		HibernateUtils.close();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> query6(String hql) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<Employee> list = null;
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
