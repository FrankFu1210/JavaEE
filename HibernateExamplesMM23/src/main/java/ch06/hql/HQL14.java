package ch06.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch00.HibernateUtils;

public class HQL14 {
	public static void main(String[] args) {
		HQL14 hq = new HQL14();
		List<Object[]> list = hq.query14("select b.bookId, b.title, b.price, bc.name from Book b join BookCompany bc on b.companyId = bc.id");
		if (list != null) {
			for (Object[] o : list) {
				System.out.printf("%3d  %8.2f  %-26s %-20s \n", o[0], o[2], o[1], o[3]);
			}
		} else {
			System.out.println("查無資料");
		}
		HibernateUtils.close();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> query14(String sql) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<Object[]> list = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			list = session.createNativeQuery(sql).getResultList();

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return list;
	}
}
