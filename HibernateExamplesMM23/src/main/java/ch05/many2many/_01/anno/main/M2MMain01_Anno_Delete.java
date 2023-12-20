package ch05.many2many._01.anno.main;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.many2many._01.anno.model.Author;
import ch05.many2many._01.anno.model.Book;

// 快樂學JSP的作者為: 劉翰林(J,S) 與 張君雅(J,S)
// Spring精典應用的作者為: 劉翰林(J,S), 張君雅(J,S) 與 黃美智
// Hibernate企業實戰的作者為: 黃美智

public class M2MMain01_Anno_Delete {
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
			System.out.println("刪除書籍");
			Book book1 = session.get(Book.class, 2);
			Set<Author> authors = new HashSet<>();
			book1.setAuthors(authors);
			book1.setBookId(2);
			session.delete(book1);
			System.out.println("------------------------------");
			System.out.println("刪除作者");
			Author author1 = session.get(Author.class, 2);
			Set<Book> books = author1.getBooks();
			for(Book bk : books) {
				bk.getAuthors().remove(author1);
			}
			author1.setBooks(books);
			author1.setAuthorId(1);
			session.delete(author1);
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
