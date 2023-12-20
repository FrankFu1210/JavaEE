package ch06.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch06.model.Employee;

public class HQL05 {
	public static void main(String[] args) {
		HQL05 hq = new HQL05();
		
//		displayData(hq.query5("FROM Employee e WHERE e.name = '張君雅' and e.depId = 1"));
		System.out.println("------------------------------");
		displayData(hq.query5("FROM Employee e WHERE e.salary >= 30000"));
		System.out.println("------------------------------");
//		displayData(hq.query5("FROM Employee e WHERE e.salary >= 30000 and e.name like '黃%'"));
		HibernateUtils.close();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> query5(String hql) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<Employee> list = null;
		Transaction tx = null; 
		try {
			tx = session.beginTransaction();
			list = session.createQuery(hql).getResultList();
			if (list.size() > 0) {
				Employee e = list.get(0);
				System.out.println(e.getName() + "=>" + e.getSalary());
				e.setSalary(e.getSalary() + 10000);
				System.out.println(e.getName() + "=>" + e.getSalary());
				System.out.println("--------------------");
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return list;
	}
	public static void displayData(List<Employee> list) {
		if (list == null) {
			System.out.println("查無資料");
			return;
		}
		for (Employee e : list) {
			System.out.printf("%3d %10s %8d %14s %2d\n", e.getId(), 
			e.getName(), e.getSalary(), e.getBirthday(), e.getDepId());
		}
	}

}
