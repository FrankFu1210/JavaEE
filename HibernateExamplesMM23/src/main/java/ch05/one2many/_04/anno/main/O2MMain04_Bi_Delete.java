
package ch05.one2many._04.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2many._04.anno.model.DepartmentBI;
// 本程式刪除一筆部門紀錄: 客戶服務部
public class O2MMain04_Bi_Delete {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try{
		// 建立SessionFactory物件
		sessionFactory = HibernateUtils.getSessionFactory();
		// 取出Session物件
		session = sessionFactory.openSession();
		System.out.println("得到Session物件");
		//開啟交易
		tx = session.beginTransaction();
		DepartmentBI dept1 = session.get(DepartmentBI.class, 1);
		session.delete(dept1);
		//Commit transaction
		tx.commit();
		session.close();
		}catch(Exception e){
			System.out.println("發生例外: "+e.getMessage());
			e.printStackTrace();
		}finally{
			if(!sessionFactory.isClosed()){
				System.out.println("關閉SessionFactory");
				sessionFactory.close();
			}
		}
	}
}

