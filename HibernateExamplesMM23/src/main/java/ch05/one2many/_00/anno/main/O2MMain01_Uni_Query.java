package ch05.one2many._00.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2many._00.anno.model.Department;
import ch05.one2many._00.anno.model.Employee;

public class O2MMain01_Uni_Query {

	public static void main(String[] args) {

		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// 建立SessionFactory物件
			sessionFactory = HibernateUtils.getSessionFactory();
			// 取出Session物件
			session = sessionFactory.openSession();
			System.out.println("得到Session物件");
			// 開啟交易
			tx = session.beginTransaction();
			// 查詢特定的Department物件，在找出其內所有Employees
			System.out.println("查詢特定的Department物件，在找出其內所有Employees:");
			Department d1 = session.get(Department.class, 1);
			if (d1 != null) {
				System.out.println("-------------------------------------");
				for (Employee emp : d1.getEmployees()) {
					System.out.println("發現ㄧ個員工，id=" + emp.getId() + ", 姓名: " + emp.getName());
				}
				System.out.println("-------------------------------------");
			} else {
				System.out.println("查無資料");
			}
			System.out.println("================================");
			// 查詢特定的Employee物件，由它找出對應的Department
			System.out.println("無法由特定的Employee物件找出對應的Department，因為Employee物件內沒有"
					+ "存放Department類別的物件");
			tx.commit();
			session.close();
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