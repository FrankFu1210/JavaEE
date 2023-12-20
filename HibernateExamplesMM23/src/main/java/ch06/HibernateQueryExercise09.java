package ch06;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;

public class HibernateQueryExercise09 {

	public static void main(String[] args) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		String hql = "UPDATE Employee e SET e.salary = e.salary + :sal, e.birthday = :birth"
				+ " WHERE e.id = :empid";
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();	
		   session.createQuery(hql)
					.setParameter("sal", 3000)
					.setParameter("birth", java.sql.Date.valueOf("1982-1-25"))
					.setParameter("empid", 5)
					.executeUpdate();
		  
		   tx.commit();
		} catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}
}
