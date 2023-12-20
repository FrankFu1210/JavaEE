package ch06;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;

public class HibernateQueryExercise03 {

	public static void main(String[] args) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		String hql = "SELECT e.salary FROM Employee e";
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();	
		
		   List<Integer> salaries = session.createQuery(hql, Integer.class)
				   					       .getResultList();
		   for(Integer salary : salaries) {
			   System.out.println(salary);
		   }
		   tx.commit();
		} catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		
	}

}
