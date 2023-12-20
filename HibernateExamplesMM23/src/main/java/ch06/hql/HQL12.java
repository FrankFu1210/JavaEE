package ch06.hql;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch06.model.Employee;


public class HQL12 {
	public static void main(String[] args) {
		HQL12 hq = new HQL12();
		int result = hq.query12();
		System.out.println("新增紀錄的筆數: " + result);
		List<Employee> list = displayData();
		if (list != null) {
			for (Employee e : list) {
				System.out.printf("%3d %8d %2d %14s %10s \n", e.getId(), 
						e.getSalary(),	e.getDepId(), e.getBirthday(), e.getName());
			}
		} else {
			System.out.println("查無資料");
		}
		HibernateUtils.close();
	}

	public int query12() {
		int n = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		// 下列都是類別與性質名稱，注意大小寫
		String hql = "INSERT INTO Employee(birthday, depId, name, salary)"
				   + "SELECT birthday, depId, name, salary FROM EmployeeA";
		Transaction tx = null;
		Query query = null;
		try {
			tx = session.beginTransaction();
			query = session.createQuery(hql);
			n = query.executeUpdate();
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		return n;
	}
	@SuppressWarnings("unchecked")
	public static List<Employee> displayData() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		String hql = "FROM Employee";
		List<Employee> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = session.createQuery(hql).getResultList();
		    tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		return list;
	}
}
