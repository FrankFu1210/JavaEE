package ch05.many2one._01.anno.main;

import ch00.HibernateUtils;
import ch05.many2one._01.anno.model.EmployeeUNI;
import ch05.many2one._01.anno.model.EmployerUNI;
import ch05.many2one._01.anno.model.dao.EmployeeDAO;

//口訣: 多對一，多方有個儲存ㄧ方物件參考實例變數，簡稱『多方有個一』
//
//只要員工類別有一個儲存雇主物件的實例變數，程式就可以由員工找到其雇主。
//但是由於是單向(Unidirectional)的多對ㄧ，所以一方不能含有多方的
//Set物件，否則就會形成雙向(Bidirectional)。
//兩個表格的關聯為於多方(員工)類別中加入能儲存一方(雇主, Employer)的物件參考
//
//Step 0: 為Employee類別、Employer類別加上應有的註釋(@Entity, @Table, @Id, ....)
//Step 1: Employee類別 
//	由於程式的需求為『由員工來找到其雇主』，因此在Employee類別內定義一個儲存
//	Employer物件參考的實例變數，即
// 		 private Employer employer;
//
//Step 2: 在此實例變數對應的getter前加上
//	@ManyToOne(cascade=CascadeType.ALL)
//	public Employer getEmployer() {
//		return employer;
//	}
//  "Many"表示本類別(Employee)，"One"表示對照類別(Employer)。
//

//Step 3: Employer類別無需特別的註釋  
//
//
public class M2O_UNI_Insert_Main01 {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		EmployerUNI emperA = new EmployerUNI("劉瑪莉老闆");	// 雇主類別
		EmployeeUNI empeeA1 = new EmployeeUNI("劉小明"); 		// 員工類別
		empeeA1.setEmployer(emperA);
		dao.save(empeeA1);
		EmployeeUNI empeeA2 = new EmployeeUNI("劉美華");		// 員工類別
		empeeA2.setEmployer(emperA);
		dao.save(empeeA2);
		
		EmployerUNI emperB = new EmployerUNI("林芳華老闆");	// 雇主類別
		EmployeeUNI empeeB1 = new EmployeeUNI("林偉明"); 		// 員工類別
		empeeB1.setEmployer(emperB);
		dao.save(empeeB1);
		EmployeeUNI empeeB2 = new EmployeeUNI("林世光");		// 員工類別
		empeeB2.setEmployer(emperB);
		dao.save(empeeB2);
		
		EmployerUNI emperC = new EmployerUNI("黃河新老闆");	// 雇主類別
		EmployeeUNI empeeC1 = new EmployeeUNI("黃天南"); 		// 員工類別
		empeeC1.setEmployer(emperC);
		dao.save(empeeC1);
	
		HibernateUtils.close();
	}
}
