package ch06.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;

public class HQL07 {
	public static void main(String[] args) {
		HQL07 hq = new HQL07();
		List<Object[]>  list = hq.query7("SELECT e.depId,  SUM(e.salary), MAX(e.salary), "
				+ " MIN(e.id) FROM Employee e GROUP BY e.depId");
		if (list != null) {
			 for(Object[] oa  : list ) {
			    	System.out.printf("%10s  %8d  %8d  %6s\n",oa[0], oa[1], oa[2], oa[3]);
		    }	
		} else {
			System.out.println("查無資料");
		}
		HibernateUtils.close();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> query7(String hql){
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<Object[]>   list = null;
		Transaction tx = null;
		try { 
			tx = session.beginTransaction();
			list = session.createQuery(hql).getResultList();
			tx.commit();   
		} catch(Exception e) {
			if (tx != null) tx.rollback();
			throw new RuntimeException(e);
		}		
		return list;
	}
}
