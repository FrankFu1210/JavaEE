package jut;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lab01.model.MemberBean;
import lab01.utils.HibernateUtils;

public class MyJUnitTest {
	SessionFactory factory;
	Session session;
	Transaction tx;
	@Before
	public void setUp() throws Exception {
		//System.out.println("@Before");
		//把前面變數型態拿掉即可
		factory = HibernateUtils.getSessionFactory();
		session = factory.openSession();
		tx = session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception {
		//System.out.println("@After");
		tx.commit();
		session.close();
		factory.close();
	}

	@Test
	public void test() {
//		fail("Not yet implemented");
		//System.out.println("@Test");
		//永續類別臨時狀態
		MemberBean memberBean = new MemberBean("AA001", "HelloKitty", "null1234", "0919-123456", 
				Date.valueOf("1990-01-01"), Timestamp.valueOf("1990-12-20 13:16:57"), 50.0);
		session.save(memberBean);
	}
	@Test
	public void test2() {//@單元測試
//		fail("Not yet implemented");
		//System.out.println("@Test2");
		MemberBean memberBean = new MemberBean("AA002", "CoocikDog", "null1234", "0919-123456", 
				Date.valueOf("1990-01-01"), new Timestamp(System.currentTimeMillis()), 57.7);
		session.save(memberBean);
	}

}
