package ch06;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch06.model.Employee;

public class HibernateQueryExercise08 {

	public static void main(String[] args) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		String hql = "FROM Employee e WHERE e.salary >= :sal and e.depId = :id";
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();	
		
		   List<Employee> emps = session.createQuery(hql, Employee.class)
				   						.setParameter("sal", 20000)
				   						.setParameter("id", 2)
				   					    .getResultList();
		   for(Employee e : emps) {
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
