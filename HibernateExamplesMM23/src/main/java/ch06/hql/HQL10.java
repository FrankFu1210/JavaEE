package ch06.hql;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;


public class HQL10 {
	public static void main(String[] args) {
		HQL10 hq = new HQL10();
		int n = hq.query10(10000, 3);
		if (n == 1) {
			System.out.println("更新一筆紀錄");
		} else {
			System.out.println("更新0筆紀錄");
		}
		HibernateUtils.close();
	}
	public int query10(int salary, int id){
		Session session = HibernateUtils.getSessionFactory().openSession();
		String hql = "UPDATE Employee set salary = salary + :salary123 "  + 
	                 "WHERE id = :employee_id";
		int result = 0;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = session.createQuery(hql)
					.setParameter("salary123", salary)
					.setParameter("employee_id", id)
					.executeUpdate();
			tx.commit();
		} catch(Exception e){
			if (tx != null)  tx.rollback();
			throw new RuntimeException(e);
		}
		return result; 
	}	
}
