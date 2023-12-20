package ch01.main;

import ch01.model.Employee;
import ch01.model.service.EmployeeService;
import ch01.model.service.impl.EmployeeServiceImpl;

public class FindEmployeeById {
	public static void main(String[] args) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		Employee emp = employeeService.findById(2);
		System.out.println("----以Session介面的get()方法依主鍵值讀取物件----\nemp=" + emp);
		employeeService.close();
	}
}
