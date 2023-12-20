package ch01.ex00.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch01.ex00.model.Member;
import ch01.ex00.service.MemberService;
import ch01.ex00.service.impl.MemberServiceImpl_Hibernate;

@WebServlet("/ch01/ex00/queryAllMembers.do")
public class QueryAllMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MemberService memberService = new MemberServiceImpl_Hibernate();
//		MemberService memberService = new MemberServiceImpl_Jdbc();  // JDBC
		Collection<Member> coll = memberService.findAll();
		request.setAttribute("AllMembers", coll);
		RequestDispatcher rd = request
				.getRequestDispatcher("/ch01/ex00/showAllMembers.jsp");
		rd.forward(request, response);
		return;
	}
}
