package ch04.ex02.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;

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
