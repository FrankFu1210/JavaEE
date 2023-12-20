package ch06;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;

public class HibernateQueryExercise10 {

	public static void main(String[] args) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		String hql = "DELETE FROM Employee e  WHERE e.id = :empid";
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();	
		   session.createQuery(hql)
					.setParameter("empid", 3)
					.executeUpdate();
		  
		   tx.commit();
		} catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
		
	}

}
