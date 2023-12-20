<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"  type="text/css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align='left'>
1. 定義 OpenSessionInViewFilter, 它的 urlPatterns 必須為會丟出下列例外的URL:<br> 
<font color='red'>org.hibernate.LazyInitializationException:</font> 
failed to lazily initialize a collection of role: 
ch04.ex02.model.Category.products, could not initialize proxy - 
<font color='red'>no Session</font><p/>
2. 編寫 OpenSessionInViewFilter.java，它的內容幾乎雷同：
<pre>
@WebFilter(urlPatterns = { "/ch04/ex02/queryCategoryById.do" })
public class OpenSessionInViewFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig fConfig;
	private SessionFactory factory;

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
		factory = HibernateUtils.getSessionFactory();
		//System.out.println("Filter已經載入...");
	}

	public void destroy() {
		factory.close();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter 的doFilter()開始執行。");
		Transaction tx = null;
		try {
			Session session = factory.getCurrentSession();
			tx = session.beginTransaction();
			System.out.println("Filter 即將執行下一棒程式");
			// 下一敘述會啟動控制器(Servlet),然後呼叫Service轉而呼叫DAO, 最後回到JSP視圖，
			// 上述這些程式執行時Session物件都保持在開啟狀態。
			chain.doFilter(request, response);
			System.out.println("Filter 由下一棒程式返回，準備執行tx.commit()");
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		System.out.println("Filter 結束一個請求與回應的流程");
	}

}
</pre>
<p/>
3. 修改相關的ServiceImpl類別內的相關方法，去掉與交易有關的敘述: 
<pre>
	@Override
	public Category findById(int id) {
		Category category = null;
<font color='red'>//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();</font>
			category = categoryDao.findById(id);
<font color='red'>//			tx.commit();
//		} catch(Exception ex) {
//			if (tx != null) {
//				tx.rollback();
//			}
//			throw new RuntimeException(ex.getMessage());
//		}</font>
		return category;
	}
</pre>	
</div>
<hr>
<div align='center'>
<a href="<c:url value='/index.jsp' />">回首頁</a>
	</div>
</body>
</html>