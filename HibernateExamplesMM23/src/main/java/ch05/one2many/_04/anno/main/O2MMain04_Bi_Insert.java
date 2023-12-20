package ch05.one2many._04.anno.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2many._04.anno.model.DepartmentBI;
import ch05.one2many._04.anno.model.EmployeeBI;

// 以資料庫的觀點而言就是在Employee類別對應的表格內增加一個外鍵欄位，注意：多方有外鍵

//Step 0: 為Department類別、Employee類別加上應有的註釋(@Entity, @Table, @Id, ....)
//Step 1: Department類別 
//		    由於程式的需求為『由部門(Department)來找出其內的員工(Employee)』，
//		    因此在Department類別內定義一個儲存Employee(多方)物件參考的Set/List物件。
//
//Step 2: 在此Set物件對應的getter前加上
//        @OneToMany(mappedBy="dept")，"One"代表本類別(Department), "Many"代表
//        對照類別(Employee)。mappedBy的屬性值必須是對方類別的性質名稱。該性質定義表示關聯的外鍵 。
//        mappedBy屬性表示本類別(Department)對應之表格並未含有可以表示記錄關聯的外鍵，
//        但是在對照類別(Employee)內的dept屬性中有外鍵的相關資訊，即下面的
//        @JoinColumn(name="fk_Dept_id", nullable=false)中的
//        fk_Dept_id就是Employee類別所對應之表格的外鍵欄位。
//        // 下面五行敘述屬於Employee類別
//        @ManyToOne  // 多對ㄧ，多方(Employee類別)內有個儲存ㄧ方(Department類別)物件參考的實例變數
//        @JoinColumn(name="fk_dept_id", nullable=false)  
//        public Department getDept() {
//	         return dept;
//        }
//Step 3: Employee類別  
//        在Employee類別內定義一個儲存Department(ㄧ方)物件參考的實例變數。
//        private Department Department;
//Step 4: Department屬性對應的getter前加上@ManyToOne，"Many"代表本類別(Employee), "One"代表
//        對照類別(Department)。
//Step 5: 再加上@JoinColumn(name="fk_dept_id", nullable=false)說明本類別(Employee)
//        對應之表格內的外鍵名稱為何。

public class O2MMain04_Bi_Insert {

	public static void main(String[] args) {
		EmployeeBI emp1 = new EmployeeBI(null, "GAM001", "劉敏珍-BI");
		EmployeeBI emp2 = new EmployeeBI(null, "GAM002", "湯元泰-BI");
		Set<EmployeeBI> set1 = new HashSet<>(Arrays.asList(emp1, emp2));
		DepartmentBI dept1 = new DepartmentBI(null, "GAM_A", "遊戲部-BI", set1);
		emp1.setDept(dept1);
		emp2.setDept(dept1);
		// ---------------------------------------------------------------
		EmployeeBI emp3 = new EmployeeBI(null, "RES001", "林信民-BI");
		EmployeeBI emp4 = new EmployeeBI(null, "RES002", "吳雅芳-BI");
		EmployeeBI emp5 = new EmployeeBI(null, "RES003", "陳智勝-BI");
		Set<EmployeeBI> set2 = new HashSet<>(Arrays.asList(emp3, emp4, emp5));
		DepartmentBI dept2 = new DepartmentBI(null, "RES_A", "餐飲部-BI", set2);
		emp3.setDept(dept2);
		emp4.setDept(dept2);
		emp5.setDept(dept2);
		// ---------------------------------------------------------------
		EmployeeBI emp6 = new EmployeeBI(null, "TRA001", "莊淑芬-BI");
		Set<EmployeeBI> set3 = new HashSet<>(Arrays.asList(emp6));
		DepartmentBI dept3 = new DepartmentBI(null, "ENG_A", "旅遊部-BI", set3);
		emp6.setDept(dept3);

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
			// Save the Model objects
			session.persist(dept1);
			session.persist(dept2);
			session.persist(dept3);
			// Commit transaction
			tx.commit();
			session.close();
			System.out.println("部門1 Id=" + dept1.getId());
			System.out.println("部門2 Id=" + dept2.getId());
			System.out.println("部門3 Id=" + dept3.getId());

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
