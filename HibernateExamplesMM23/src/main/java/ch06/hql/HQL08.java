package ch06.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch06.model.Employee;

public class HQL08 {
	public static void main(String[] args) {
		HQL08 hq = new HQL08();
		List<Employee> list = hq.query8_A(1);
		if (list != null) {
			for (Employee e : list) {
				System.out.printf("%2d %6s %6d %24s %2d\n", e.getId(), e.getName(), 
						e.getSalary(), e.getBirthday(),	e.getDepId());
			}
		} else {
			System.out.println("查無資料");
		}
		System.out.println("-------------------------------------------");
		Employee emp = hq.query8_B(20);
		if (emp != null) {
			System.out.printf("%2d %6s %6d %24s %2d\n", emp.getId(), emp.getName(), 
					emp.getSalary(), emp.getBirthday(),	emp.getDepId());
		} else {
			System.out.println("查無資料");
		}
		HibernateUtils.close();
	}

	// Using Named Paramters
	@SuppressWarnings("unchecked")
	public List<Employee> query8_A(Object arg) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		String hql = "FROM Employee e WHERE e.depId = :dep_id";
		List<Employee> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = session.createQuery(hql).setParameter("dep_id", arg).getResultList();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return list;
	}

	public Employee query8_B(Object arg) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();

		String hql = "FROM Employee e WHERE e.id = :emp_id";
		Employee emp = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			emp = (Employee) session.createQuery(hql).setParameter("emp_id", arg).getSingleResult();
			tx.commit();
		} catch (javax.persistence.NoResultException e) {
			emp = null;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return emp;
	}
}
