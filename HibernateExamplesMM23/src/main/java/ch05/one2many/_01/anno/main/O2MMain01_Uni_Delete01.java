package ch05.one2many._01.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2many._01.anno.model.Cart;

public class O2MMain01_Uni_Delete01 {

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
			// 
//			System.out.println("刪除Items物件(參考別的物件的一方)不會丟出例外:");
//			Item i1 = session.get(Item.class, 1L); 
//			session.delete(i1);
			
			System.out.println("刪除Cart物件(被參考的一方而且沒有使用"  
			+ "CascadeType.REMOVE / CascadeType.ALL), 而且外鍵欄位使用 nullable = false 會丟出例外:");
			// 如果被參考的一方, 即Cart物件，有使用CascadeType.REMOVE / CascadeType.ALL，
			// 則刪除Cart物件會連帶刪除Item物件。
			Cart c2 = session.get(Cart.class, 2L);
			if (c2 != null) {
				session.delete(c2);
			} else {
				System.out.println("null 物件無法刪除");
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