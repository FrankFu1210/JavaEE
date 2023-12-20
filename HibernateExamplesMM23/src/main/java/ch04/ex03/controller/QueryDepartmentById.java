package ch04.ex03.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch01.model.Department;
import ch01.model.service.DepartmentService;
import ch01.model.service.impl.DepartmentServiceImpl;

@WebServlet("/ch04/ex03/queryDepartmentById.do")

public class QueryDepartmentById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String depId = request.getParameter("depId");
		int id = 0;
		if (depId != null) {
			id = Integer.parseInt(depId.trim());
		}
		DepartmentService departmentService = new DepartmentServiceImpl(); 
		Department department = departmentService.findById(id);
		request.setAttribute("department", department);
		
		System.out.println("department=" + department);
		System.out.println("getEmployees=" + department.getEmployees());
		
		RequestDispatcher rd  = request.getRequestDispatcher("/ch04/ex03/showEmployeesByDepartment.jsp");      
		rd.forward(request, response);  
		return;
	}
}