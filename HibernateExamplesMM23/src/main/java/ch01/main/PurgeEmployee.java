package ch01.main;

import ch01.model.service.EmployeeService;
import ch01.model.service.impl.EmployeeServiceImpl;

public class PurgeEmployee {
	public static void main(String[] args) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		employeeService.delete(3);
		employeeService.close();
	}
}
