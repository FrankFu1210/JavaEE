package ch01.main;

import java.util.List;

import ch01.model.Employee;
import ch01.model.service.EmployeeService;
import ch01.model.service.impl.EmployeeServiceImpl;

public class QueryAllEmployees {
	public static void main(String[] args) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		List<Employee> list = employeeService.findAll();
		for(Employee emp : list) {
			System.out.println(emp);
		}
		employeeService.close();
	}
}
