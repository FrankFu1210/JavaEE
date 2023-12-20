package ch01.ex00.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch00.SystemUtils;
import ch01.ex00.model.Member;
import ch01.ex00.service.MemberService;
import ch01.ex00.service.impl.MemberServiceImpl_Hibernate;

@WebServlet("/ch01/ex00/registerMember.do")
public class RegisterMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		// 準備存放錯誤訊息的 List 物件
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMsg);
		// 1. 讀取使用者輸入資料
		String userId = request.getParameter("userId");
		String password = request.getParameter("pswd");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String phoneNo = request.getParameter("phoneNo");
		String expericnceStr = request.getParameter("experience");
		int experience = 0;
		// 2. 進行必要的資料轉換
		if (expericnceStr == null || expericnceStr.trim().length() == 0) {
			errorMsg.put("experience", "工作經驗欄必須輸入");
		} else {
			try {
				experience = Integer.parseInt(expericnceStr.trim());
			} catch (NumberFormatException e) {
				errorMsg.put("experience", "工作經驗欄格式錯誤，應該為整數");
			}
		}
		// 3. 檢查使用者輸入資料
		if (userId == null || userId.trim().length() == 0) {
			errorMsg.put("userId", "會員代號欄必須輸入");
		}
		if (password == null || password.trim().length() == 0) {
			errorMsg.put("pswd", "密碼欄必須輸入");
		}
		if (name == null || name.trim().length() == 0) {
			errorMsg.put("name", "姓名欄必須輸入");
		}
		
		java.sql.Date date = null;
		if (birthday != null && birthday.trim().length() > 0) {
			try {
				date = SystemUtils.toSqlDate(birthday);
			} catch (Exception e) {
				errorMsg.put("birthday", "生日欄格式錯誤");
			}
		}
		
		if (phoneNo == null || phoneNo.trim().length() == 0) {
			errorMsg.put("phoneNo", "電話號碼欄必須輸入");
		}
		if (experience < 0) {
			errorMsg.put("experience", "工作經驗應該為正整數或 0 ");
		}
		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/ch01/ex00/registerMember.jsp");
			rd.forward(request, response);
			return;
		}
		// 4. 進行 Business Logic 運算
		MemberService ms = new MemberServiceImpl_Hibernate(); 
//		MemberService ms = new MemberServiceImpl_Jdbc();  // JDBC 
		if (ms.existsByUserId(userId)) {
			errorMsg.put("userId", "該代號 (" + userId + ") 已經存在，請換新的代號");
		} else {
			Timestamp registerTime = new Timestamp(System.currentTimeMillis());
			Member mem = new Member(null, userId, password, name, phoneNo, experience, date, registerTime);
			try {
				Member m = ms.save(mem);
				System.out.println(m);
			} catch (Exception e) {
				errorMsg.put("userId", "儲存資料時發生錯誤，請檢查，例外=" + e.getMessage());
				e.printStackTrace();
			}
		}
		// 5.依照 Business Logic 運算結果來挑選適當的畫面
		HttpSession session = request.getSession();
		session.setAttribute("userIdKey", userId);  
		session.setAttribute("name", name);  
		if (errorMsg.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/ch01/ex00/registerSuccess.jsp");  
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/ch01/ex00/registerMember.jsp");
			rd.forward(request, response);
			return;
		}
	}
}