package ch06;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ch00.HibernateUtils;

public class HibernateQueryExercise12 {

	public static void main(String[] args) {
		Session session = HibernateUtils.getSessionFactory().openSession();
	    // 下列都是類別與性質名稱，注意大小寫
	    String hql = "INSERT INTO Employee(birthday, depId, name, salary)"
	               + "SELECT birthday, depId, name, salary FROM EmployeeA";
	    Transaction tx = null;
	    Query<?> query = null;
	    try {
	        tx = session.beginTransaction();
	        query = session.createQuery(hql);
	        query.executeUpdate();
	        
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null)
	            tx.rollback();
	        e.printStackTrace();
	    }
	}

}
