package ch06;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;

public class HibernateQueryExercise13 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String sql = "SELECT * FROM ch06_EMPLOYEE";
		Session session = HibernateUtils.getSessionFactory().openSession();
	    List<Object[]> list = null;
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        list = session.createNativeQuery(sql).list();
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null)
	            tx.rollback();
	        throw new RuntimeException(e);
	    }
	    for(Object[] oa :list) {
	    	for(Object obj : oa) {
	    		System.out.print(obj+ " ");
	    	}
	    	System.out.println();
	    }
	}
}
