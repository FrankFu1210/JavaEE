package lab01.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab01.SystemUtils;
import lab01.model.MemberBean;
import lab01.service.MemberService;
import lab01.service.impl.MemberServiceImpl;

@WebServlet("/lab01/updateMember.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession hsession = request.getSession();
		Map<String,String> errorMessage = new HashMap<>();
		hsession.setAttribute("error", errorMessage);
		String modify = request.getParameter("finalDecision");
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		MemberService ms = new MemberServiceImpl();
		
		if(modify.equalsIgnoreCase("DELETE")){
			ms.deleteById(id);
			hsession.setAttribute("DeleteSuccess", "刪除成功");
			String url = request.getContextPath() + "/lab01/queryMember.do";
			String newurl = response.encodeRedirectURL(url);
			response.sendRedirect(newurl);
			
		}else if(modify.equalsIgnoreCase("UPDATE")){
			// 讀取瀏覽器送來的 memberId 欄位內的資料
			String memberId = request.getParameter("memberId");
			// 讀取使用者所輸入，由瀏覽器送來的 pswd 欄位內的資料，注意大小寫
			String password = request.getParameter("pswd");
			// 檢查使用者所輸入的資料
			if (password == null || password.trim().length() == 0) {
				errorMessage.put("password", "密碼欄必須輸入");
			}
			// 讀取使用者所輸入，由瀏覽器送來的 name 欄位內的資料
			String name = request.getParameter("name");
			// 檢查使用者所輸入的資料
			if (name == null || name.trim().length() == 0) {
				errorMessage.put("name", "姓名欄必須輸入");
			}
			// 讀取使用者所輸入，由瀏覽器送來的 phone 欄位內的資料
			String phone = request.getParameter("phone");
			// 讀取使用者所輸入，由瀏覽器送來的 birthday 欄位內的資料
			String birthday = request.getParameter("birthday");
			java.sql.Date date = null;
			if (birthday != null && birthday.trim().length() > 0) {
				try {
					date = SystemUtils.toSqlDate(birthday);
				} catch (Exception e) {
					errorMessage.put("birthday", "生日欄格式錯誤");
				}
			}
			String weight = request.getParameter("weight");
			double dWeight = -1;
			if (weight != null && weight.trim().length() > 0) {
				try {
					dWeight = Double.parseDouble(weight.trim());
				} catch (NumberFormatException e) {
					errorMessage.put("weight", "體重欄必須為數值");
				} 
			}
			if (!errorMessage.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/lab01/updateMember.jsp");
				rd.forward(request, response);
				return;
			}
			
			MemberBean mb = new MemberBean(memberId, name, password, phone, date, null, dWeight);
			mb.setId(id);
			ms.update(mb);
			hsession.setAttribute("modify", "修改成功");
			String url = request.getContextPath() + "/lab01/queryMember.do";
			String newurl = response.encodeRedirectURL(url);
			response.sendRedirect(newurl);
		}
	}
}
