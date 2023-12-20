package ch05.one2many._04.anno.main;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2many._04.anno.model.DepartmentBI;
import ch05.one2many._04.anno.model.EmployeeBI;

//
public class O2MMain04_Bi_Update_Persistent {
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
			// 更新一方物件
			DepartmentBI dept1 = session.get(DepartmentBI.class, 1);
			dept1.setDeptName("客戶關係部");
			// 客戶關係部新增一位員工:
			Set<EmployeeBI> employees1 = dept1.getEmployees();
			employees1.add(new EmployeeBI(null, "CUS003", "劉曉馨"));
			dept1.setEmployees(employees1);
			System.out.println("===============================================");
			// 會計部刪除一位員工:
			DepartmentBI dept2 = session.get(DepartmentBI.class, 2);
			Set<EmployeeBI> employees2 = dept2.getEmployees();
			Iterator<EmployeeBI> it = employees2.iterator();
			// "劉芳" 是否仍在Employee表格中必須視@OneToMany是否有加入orphanRemoval = true，
			// 如 @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
			while (it.hasNext()) {
				EmployeeBI e = it.next();
				if (e.getName().equals("劉芳"))
					it.remove();
			}
			System.out.println("===============================================");
			// 工程部修改一位員工:
			DepartmentBI dept3 = session.get(DepartmentBI.class, 3);
			Set<EmployeeBI> employees3 = dept3.getEmployees();
			Iterator<EmployeeBI> it3 = employees3.iterator();
			while (it3.hasNext()) {
				EmployeeBI e = it3.next();
				if (e.getName().equals("莊明"))
					e.setName("莊光明");
			}
			tx.commit();  // Commit transaction
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
