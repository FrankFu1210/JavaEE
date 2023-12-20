package ch04.ex01.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch04.ex01.model.Category;
import ch04.ex01.service.CategoryService;
import ch04.ex01.service.impl.CategoryServiceImpl;

@WebServlet("/ch04/ex01/queryAllCategories.do")
public class QueryAllCategoriesServlet_Eager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CategoryService categoryService;
	
	public QueryAllCategoriesServlet_Eager() {
		this.categoryService = new CategoryServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = categoryService.findAll();
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("/ch04/ex01/showCategories.jsp");
		rd.forward(request, response);
		return;
	}

}
