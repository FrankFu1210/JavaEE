package ch05.many2one._01.anno.main;

import ch00.HibernateUtils;
import ch05.many2one._01.anno.model.EmployeeUNI;
import ch05.many2one._01.anno.model.dao.EmployeeDAO;

public class M2O_UNI_Query_Main {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		int key = 1;
		EmployeeUNI emp = dao.findByPrimaryKey(key);
		if (emp != null) {
			System.out.println(emp);
		} else {
			System.out.println("查無此筆資料, 鍵值=" + key);
		}
		key = 22;
		EmployeeUNI emp2 = dao.findByPrimaryKey(key);
		if (emp2 != null) {
			System.out.println(emp2);
		} else {
			System.out.println("查無此筆資料, 鍵值=" + key);
		}
		HibernateUtils.close();
	}
}
