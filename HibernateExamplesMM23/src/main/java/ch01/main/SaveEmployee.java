package ch01.main;

import java.sql.Date;

import ch00.HibernateUtils;
import ch01.model.Department;
import ch01.model.Employee;
import ch01.model.service.EmployeeService;
import ch01.model.service.impl.EmployeeServiceImpl;
 
public class SaveEmployee {

	public static void main(String[] args) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		Department dept1 = new Department(null, "行銷部");
		Department dept2 = new Department(null, "工程部");
		Department dept3 = new Department(null, "會計部");
		
		Employee emp1 = new Employee(null, "A033", "劉麗芳", 56000, 57.6, 
				                     Date.valueOf("1980-1-5"), dept1);
		employeeService.save(emp1);
		
		Employee emp2 = new Employee(null, "A070", "葉美華", 45000, 66.7, 
                                     Date.valueOf("1987-8-9"), dept1);
		employeeService.save(emp2);
		
		Employee emp3 = new Employee(null, "A120", "林國忠", 37000, 64.0, 
                Date.valueOf("1992-6-18"), dept1);
		
		employeeService.save(emp3);
		
		Employee emp4 = new Employee(null, "B501", "黃湘", 48000, 60.0, 
                Date.valueOf("1990-5-17"), dept2);
		
		employeeService.save(emp4);
		
		Employee emp5 = new Employee(null, "C702", "劉德佳", 43500, 62.0, 
                Date.valueOf("1997-5-2"), dept3);
		
		employeeService.save(emp5);
		
		Employee emp6 = new Employee(null, "C715", "林曉真", 55000, 68.7, 
                Date.valueOf("1988-12-12"), dept3);
		
		employeeService.save(emp6);
		
		HibernateUtils.getSessionFactory().close();
		
	}
}
// 執行session物件的persist()方法，將emp物件寫入資料庫的Employee_Table表格
// session.persist(emp); ==> cascade=CascadeType.PERSIST
// session.save(emp); ==> cascade=CascadeType.ALL