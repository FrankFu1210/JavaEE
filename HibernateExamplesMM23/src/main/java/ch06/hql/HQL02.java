package ch06.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch06.model.Employee;

public class HQL02 {
	public static void main(String[] args) {
		HQL02 hq = new HQL02();
		List<Employee> list1 = hq.query2("FROM Employee AS e");
		if (list1 != null) {
			for (Employee e : list1) {
				System.out.printf("%3d %10s %8d %14s %2d\n", e.getId(),	e.getName(), 
						e.getSalary(), e.getBirthday(),	e.getDepId());
			}
		} else {
			System.out.println("查無資料");
		}
		System.out.println("---------------------");
		List<Employee> list2 = hq.query2("FROM Employee e");
		if (list2 != null) {
			for (Employee e : list2) {
				System.out.printf("%3d %10s %8d %14s %2d\n", e.getId(),	e.getName(), 
						e.getSalary(), e.getBirthday(),	e.getDepId());
			}
		} else {
			System.out.println("查無資料");
		}

		HibernateUtils.close();
	}

	public List<Employee> query2(String hql) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<Employee> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = session.createQuery(hql, Employee.class)
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
