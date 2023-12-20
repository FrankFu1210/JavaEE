package ch05.many2many._01.anno.main;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.many2many._01.anno.model.Author;
import ch05.many2many._01.anno.model.Book;

//口訣: 多對多，雙方都有個可以儲存對方物件參考的List/Set物件，簡稱『多方有個多』
//
//
//Step 0: 為Author類別、Book類別加上應有的註釋(@Entity, @Table, @Id, ....)
//Step 1: Book類別 
//定義一個儲存Author物件參考的Set物件，即
//	private Set<Author> authors = new HashSet<Author>(0);
//  
//  接著為authors變數對應的getter()方法定義下列註釋
//	@JoinTable(name = "author_book", 
//		joinColumns = { 
//			@JoinColumn(name = "JT_BOOK_ID", nullable = false, updatable = false) 
//		}, 
//		inverseJoinColumns = { 
//			@JoinColumn(name = "JT_AUTHOR_ID",	nullable = false, updatable = false) 
//		}
//	)
//  joinColumns = {@JoinColumn(name = "FK_BOOK_ID" // 參考Book表格的說明
//  inverseJoinColumns = { @JoinColumn(name = "FK_AUTHOR_ID",  // 參考Book表格的說明


//
//Step 2: Author類別 
//定義一個儲存Book物件參考的Set物件，即
//	private Set<Book> books = new HashSet<Book>(0);
//  
//Step 3: Author類別無需特別的註釋  
//
//

public class M2MMain01_Anno_Insert {
	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// 建立SessionFactory物件
			sessionFactory = HibernateUtils.getSessionFactory();
			// 取出Session物件
			session = sessionFactory.openSession();
			System.out.println("得到Session物件");
			// 開啟交易
			tx = session.beginTransaction();
			Book book1 = new Book("快樂學JSP");
			Book book2 = new Book("Hibernate企業實戰");
			Book book3 = new Book("Spring精典應用");
			Author a1 = new Author("張君雅(J,S)");
			Author a2 = new Author("劉翰林(J,S)");
			Author a3 = new Author("黃美智(H,S)");

			Set<Author> set_s = new HashSet<Author>();
			set_s.add(a1); 
			set_s.add(a2); 
			set_s.add(a3);
			
			Set<Author> set_j = new HashSet<Author>();
			set_j.add(a1); 
			set_j.add(a2);
			
			Set<Author> set_h = new HashSet<Author>();
			set_h.add(a3);

			book1.setAuthors(set_j);
			book2.setAuthors(set_h);
			book3.setAuthors(set_s);
			
			session.save(book1);
			session.save(book2);
			session.save(book3);
//			String author = "";
//			for(Author a : book1.getAuthors()){
//				author += a.getAuthorName() + " ";
//			}
//			System.out.println(book1.getBookName() + "===>" + author);
//			System.out.println("------------------------------------");
//			author = "";
//			for(Author a : book2.getAuthors()){
//				author += a.getAuthorName() + " ";
//			}
//			System.out.println(book2.getBookName() + "===>" + author);
//			System.out.println("------------------------------------");
//			author = "";
//			for(Author a : book3.getAuthors()){
//				author += a.getAuthorName() + " ";
//			}
//			System.out.println(book3.getBookName() + "===>" + author);
//			System.out.println("------------------------------------");
			
			tx.commit();
			System.out.println("程式執行完畢");
		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (!sessionFactory.isClosed()) {
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}
}
