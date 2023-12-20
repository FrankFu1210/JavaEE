package ch04.ex03.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch01.model.Department;
import ch01.model.service.DepartmentService;
import ch01.model.service.impl.DepartmentServiceImpl;

@WebServlet("/ch04/ex03/queryAllDepartments.do")

public class QueryAllDepartments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		DepartmentService departmentService = new DepartmentServiceImpl(); 
		List<Department> departments = departmentService.findAll();
		request.setAttribute("departments", departments);
		RequestDispatcher rd  = request.getRequestDispatcher("/ch04/ex03/showDepartments.jsp");      
		rd.forward(request, response);  
		return;
	}
}