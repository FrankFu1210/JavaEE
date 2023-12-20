package ch01.ex00.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch01.ex00.model.Member;
import ch01.ex00.service.MemberService;
import ch01.ex00.service.impl.MemberServiceImpl_Hibernate;

@WebServlet("/ch01/ex00/FindMemberServlet.do")
public class FindMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		int iid = Integer.parseInt(id);
		MemberService memberService = new MemberServiceImpl_Hibernate();
//		MemberService memberService = new MemberServiceImpl_Jdbc();  // JDBC
		Member member = memberService.findById(iid);
		System.out.println(member);
		request.setAttribute("member", member);
		RequestDispatcher rd = request.getRequestDispatcher("/ch01/ex00/updateMember.jsp");
		rd.forward(request, response);
		return;
	}
}