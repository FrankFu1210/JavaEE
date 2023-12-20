package ch05.many2many._01.anno.main;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch00.HibernateUtils;
import ch05.many2many._01.anno.model.Author;
import ch05.many2many._01.anno.model.Book;

// 快樂學JSP的作者為: 劉翰林(J,S) 與 張君雅(J,S)
// Hibernate企業實戰的作者為: 黃美智
// Spring精典應用的作者為: 劉翰林(J,S), 張君雅(J,S) 與 黃美智

public class M2MMain01_Anno_Query {
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
			System.out.println("由書找作者:");
			Book book1 = session.get(Book.class, 1);
//			Book book2 = session.get(Book.class, 2);
//			Book book3 = session.get(Book.class, 3);
//			System.out.println("由作者找書:");
//			Author author1 = session.get(Author.class, 1);
//			Author author2 = session.get(Author.class, 2);
//			Author author3 = session.get(Author.class, 3);
			
			Set<Author> a1 = book1.getAuthors();
			System.out.println("書籍:" + book1.getBookName() + "的作者: " + a1);
			System.out.println("-------------------------------------------");
//			Set<Author> a2 = book2.getAuthors();
//			System.out.println("書籍:" + book2.getBookName() + "的作者: " + a2);
//			System.out.println("-------------------------------------------");
//			Set<Author> a3 = book3.getAuthors();
//			System.out.println("書籍:" + book3.getBookName() + "的作者: " + a3);
//			System.out.println("==============================================");
//			Set<Book> b1 = author1.getBooks();
//			System.out.println("作者:" + author1.getAuthorName() + "編寫的書籍: " + b1);
//			System.out.println("-------------------------------------------");
//			Set<Book> b2 = author2.getBooks();
//			System.out.println("作者:" + author2.getAuthorName() + "編寫的書籍: " + b2);
////			System.out.println("-------------------------------------------");
//			Set<Book> b3 = author3.getBooks();
//			System.out.println("作者:" + author3.getAuthorName() + "編寫的書籍: " + b3);
//			System.out.println("-------------------------------------------");
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
/*
SELECT
    ab.FK_AUTHOR_ID,
    ab.FK_BOOK_ID,
    b.BOOK_ID AS BOOK_ID1_2_1_,
    b.book_name AS book_nam2_2_1_,
    a.AUTHOR_NAME 
    FROM
        author_book ab 
    INNER JOIN
        Book_M2M_01_ANNO b 
            ON ab.FK_BOOK_ID=b.BOOK_ID 
    INNER JOIN
        Author_M2M_01_ANNO a 
            ON ab.FK_AUTHOR_ID=a.AUTHOR_ID             
    WHERE
        ab.FK_AUTHOR_ID=2
*/        
