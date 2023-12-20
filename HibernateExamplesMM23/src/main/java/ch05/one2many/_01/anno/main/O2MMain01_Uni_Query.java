package ch05.one2many._01.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2many._01.anno.model.Cart;
import ch05.one2many._01.anno.model.Item;

public class O2MMain01_Uni_Query {

	public static void main(String[] args) {

		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// 建立SessionFactory物件
			sessionFactory = HibernateUtils.getSessionFactory();
			// 取出Session物件
			session = sessionFactory.getCurrentSession();
			System.out.println("得到Session物件");
			// 開啟交易
			tx = session.beginTransaction();
			// 查詢特定的Cart物件，在找出其內所有Items
			System.out.println("查詢特定的Cart物件，在找出其內所有Items:");
			Cart c = session.get(Cart.class, 1L);
			for (Item item : c.getItemsAnno()) {
				System.out.println("發現ㄧ個item，id=" + item.getItemId());
			}
			tx.commit();
		} catch (Exception e) {
			System.out.println("發生例外: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (!sessionFactory.isClosed()) {
				System.out.println("關閉SessionFactory");
				sessionFactory.close();
			}
		}
	}
}