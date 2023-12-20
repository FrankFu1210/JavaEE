
package ch05.one2many._00.anno.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.one2many._00.anno.model.Department;
import ch05.one2many._00.anno.model.Employee;
//單向一對多: 由部門(Department)來找出其內所有員工(Employee)，但無法由員工找到部門。
//口訣: 一對多，一方有個儲存多方物件參考的List/Set物件，簡稱『ㄧ方有個多』
// 
//只要部門類別(Department)內含有一個能儲存多方物件(員工類別)的Set物件，
//程式就可以由部門找到其內所有員工。但是由於是單向(Unidirectional)的ㄧ對多，
//所以多方(Employee)不能含有一方(Department)的物件參考，否則就會形成雙向(Bidirectional)。
//但是兩個表格仍要能保有關聯，於多方(Employee)類別中加入能儲存一方主鍵值的屬性(不是物件參考)
//是唯一的做法。
// 
//以資料庫的觀點而言就是在Employee類別對應的表格內增加一個外鍵欄位，此欄位儲存該Employee物件
//所屬之部門的主鍵，Hibernate就可以由部門(Department)來找出其內所有員工(Employee)。
//
//Step 0: 為Department類別、Employee類別加上應有的註釋(@Entity, @Table, @Id, ....)
//Step 1: Department類別 
//		    由於程式的需求為『由部門(Department)來找出其內的員工(Employee)』，
//		    因此在Department類別內定義一個儲存Employee(多方)物件參考的Set物件，即
//        private Set<Employee> Employees = new LinkedHashSet<>();
//
//Step 2: 在此Set物件對應的getter前加上
//        1. @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//        2. @JoinColumn(name="fk_dept_id", referencedColumnName="dept_id")
//           ------------------------------------------------------------------
//        A. "One"表示本類別(Department), "Many"表示對照類別(Employee)。
//           cascade=CascadeType.ALL: 當一方的Set欄位存放多方的物件參考後，
//           儲存一方物件會自動儲存多方物件
//            
//        B. @JoinColumn說明說明多方表格的fk_dept_id欄位為外鍵欄位(即Employee類別對應的表格有fk_dept_id欄位)，
//           參考(或說存放)ㄧ方(Department)的欄位為dept_id，多方類別若未定義對應的屬性，Hibernate會自動產生
//           該欄位。
//                
//Step 3: Employee類別 : 可以定義，也可以不定義下面的欄位。如果不定義下面欄位，Hibernate會代為定義。
//        在Employee類別內定義一個儲存ㄧ方類別(Department)主鍵值(注意，不是一方的物件參考)的實例變數。
//private Integer deptId;
//@Column(name="fk_Dept_id")
//public Integer getDeptId() {
//	return deptId;
//}
//public void setDeptId(Integer deptId) {
//	this.deptId = deptId;
//}
//
// 本程式新增三筆部門紀錄: 客戶服務部、會計部、工程部
public class O2MMain01_Uni_Insert {

	public static void main(String[] args) {
		Employee emp1 = new Employee(null, "CUS001", "黃華");
		Employee emp2 = new Employee(null, "CUS002", "林曉真");
		Set<Employee>   set1 = new HashSet<>(Arrays.asList(emp1, emp2));
		Department dept1 = new Department(null, "CUS_A", "客戶服務部", set1);
// ---------------------------------------------------------------
		Employee emp3 = new Employee(null, "ACC001", "劉芳");
		Employee emp4 = new Employee(null, "ACC002", "張君雅");
		Employee emp5 = new Employee(null, "ACC003", "陳淑芳");
		Set<Employee>   set2 = new HashSet<>(Arrays.asList(emp3, emp4, emp5));
		Department dept2 = new Department(null, "ACC_A", "會計部", set2);
// ---------------------------------------------------------------		
		Employee emp6 = new Employee(null, "ENG001", "莊明");
		Set<Employee>   set3 = new HashSet<>(Arrays.asList(emp6));
		Department dept3 = new Department(null, "ENG_A", "工程部", set3);
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
		
//      因為Department類別有下列註釋，所以下面的for()迴圈可以省略
//      @OneToMany(cascade=CascadeType.ALL)
// 		不能使用(cascade=CascadeType.PERSIST)
//		for(Employee employee : set1){
//			session.save(employee);
//		}
//		for(Employee employee : set2){
//			session.save(employee);
//		}
//		for(Employee employee : set3){
//			session.save(employee);
//		}		
		
		//Save the Model objects
		session.save(dept1);
		session.save(dept2);
		session.save(dept3);
		//Commit transaction
		System.out.println("===============================================");
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

