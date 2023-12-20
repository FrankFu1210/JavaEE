package ch06;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch06.model.Employee;

public class HibernateQueryExercise11 {

	public static void main(String[] args) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		String hql = "FROM Employee e ORDER By e.id";
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();	
		   List<Employee> emps = session.createQuery(hql, Employee.class)
					.setFirstResult(4)
					.setMaxResults(2)
					.getResultList();
		   for (Employee e : emps) {
				System.out.println(e);
			}
		   tx.commit();
		} catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		
	}

}
