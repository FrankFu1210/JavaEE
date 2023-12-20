package ch05.one2many._04.anno.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2many._04.anno.model.DepartmentBI;
import ch05.one2many._04.anno.model.EmployeeBI;

public class O2MMain04_Bi_Query {

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
			// 查詢特定的Department物件，在找出其內所有Employees
			System.out.println("查詢特定的Department物件，在找出其內所有Employees:");
			System.out.println("-------------------------------------------");
			DepartmentBI dept = session.get(DepartmentBI.class, 1);
			System.out.println("-------------------------------------------");
			for (EmployeeBI Employee : dept.getEmployees()) {
				System.out.println("發現ㄧ個Employee，id=" + Employee.getEmployeeId());
			}
			System.out.println("-------------------------------------------");
			System.out.println("================================");
			// 查詢特定的Employee物件，由它找出對應的Department
			System.out.println("查詢特定的Employee物件，由它找出對應的Department:");
			int EmployeeKey = 3;
			EmployeeBI i = session.get(EmployeeBI.class, EmployeeKey);
			String deptName = i.getDept().getDeptName();
			System.out.println("Employee id=" + EmployeeKey + "的部門為" + deptName);
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