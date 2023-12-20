package ch05.many2one._01.anno.main;

import ch00.HibernateUtils;
import ch05.many2one._01.anno.model.EmployeeUNI;
import ch05.many2one._01.anno.model.dao.EmployeeDAO;
import ch05.many2one._01.anno.model.dao.EmployerDAO;

public class M2O_UNI_Delete_Main {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		EmployerDAO daoer = new EmployerDAO();
		int key = 0, fkey = 0;
		int n = 0, deleteCount = 0; 
		key = 1;
		// 由於此例為單向，無法由Employer找回Employee
		EmployeeUNI emp = dao.findByPrimaryKey(key);
		if (emp != null) {
			fkey = emp.getEmployer().getId();
			
		}
		n = dao.deleteByPrimaryKey(key);
		if (n != 0) {
			System.out.println("編號: "+ key + " 刪除成功");
			int emperCount = dao.findEmployeeAmount(fkey);
			System.out.println("emperCount=" + emperCount);
			if (emperCount == 0) {
				deleteCount = daoer.deleteByPrimaryKey(fkey);
				if (deleteCount == 1) {
					System.out.println("雇主已經刪除");
				}
			}
		} else {
			System.out.println("編號: "+ key + " 刪除失敗");
		}
		key = 2;
		emp = dao.findByPrimaryKey(key);
		if (emp != null) {
			fkey = emp.getEmployer().getId();
		
		}
		n = dao.deleteByPrimaryKey(key);
		if (n != 0) {
			System.out.println("編號: "+ key + " 刪除成功");
			int emperCount = dao.findEmployeeAmount(fkey);
			System.out.println("emperCount=" + emperCount);
			if (emperCount == 0) {
				deleteCount = daoer.deleteByPrimaryKey(fkey);
				if (deleteCount == 1) {
					System.out.println("雇主已經刪除");
				}
			}
		} else {
			System.out.println("編號: "+ key + " 刪除失敗");
		}
		
		HibernateUtils.close();
	}
}
