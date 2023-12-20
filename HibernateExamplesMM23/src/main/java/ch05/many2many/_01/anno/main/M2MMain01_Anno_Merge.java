package ch05.many2many._01.anno.main;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.many2many._01.anno.model.Author;
import ch05.many2many._01.anno.model.Book;

// 快樂學JSP的作者為: 劉翰林(J,S) 與 張君雅(J,S)
// Spring精典應用的作者為: 劉翰林(J,S), 張君雅(J,S) 與 黃美智(H,S)
// Hibernate企業實戰的作者為: 黃美智(H,S)

public class M2MMain01_Anno_Merge {
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
			// 
			tx = session.beginTransaction();
			System.out.println("修改書籍(Hibernate企業實戰)");
			Book book1 = session.get(Book.class, 3);
			Set<Author> authors = book1.getAuthors();
			Hibernate.initialize(authors);
			System.out.println("----------------------------------");
			tx.commit();
			session.close();
			Iterator<Author> it = authors.iterator();
			while (it.hasNext()) {
				Author author = it.next();
				String name = author.getAuthorName();
				if (!name.equals("黃美智(H,S)")) {
					it.remove();
				}
			}
			Author a1 = new Author("梁國華");
			authors.add(a1);
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			session.merge(book1);
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
