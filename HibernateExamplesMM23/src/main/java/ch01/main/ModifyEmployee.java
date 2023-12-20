package ch01.main;

import ch01.model.Department;
import ch01.model.Employee;
import ch01.model.service.DepartmentService;
import ch01.model.service.EmployeeService;
import ch01.model.service.impl.DepartmentServiceImpl;
import ch01.model.service.impl.EmployeeServiceImpl;

public class ModifyEmployee {
	public static void main(String[] args) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		DepartmentService departmentService = new DepartmentServiceImpl();
		
		Employee emp = employeeService.findById(2);
		if (emp==null) {
			System.out.println("查無此物件，無法更新, 程式結束...");
			return;
		}  
		Department dept = departmentService.findByIdWithoutLazyLoading(1);
		System.out.println("dept----->" + dept);
		emp.setDept(dept);
		emp.setName("林郁華");
		employeeService.update(emp);
		System.out.println("更新完畢");
		employeeService.close();
	}
}
