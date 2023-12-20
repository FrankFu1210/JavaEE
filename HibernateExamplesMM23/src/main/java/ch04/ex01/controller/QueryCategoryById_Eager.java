package ch04.ex01.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch04.ex01.model.Category;
import ch04.ex01.service.CategoryService;
import ch04.ex01.service.impl.CategoryServiceImpl;

@WebServlet("/ch04/ex01/queryCategoryById.do")
public class QueryCategoryById_Eager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CategoryService categoryService ;
	
	public QueryCategoryById_Eager() {
		this.categoryService = new CategoryServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("catId");
		int id = -1;
		try {
			id = Integer.parseInt(sid);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Category category = categoryService.findById(id);
		request.setAttribute("category", category);
		RequestDispatcher rd= request.getRequestDispatcher("/ch04/ex01/showProductsByCategory.jsp");
		rd.forward(request, response);
		return;
	}
}
