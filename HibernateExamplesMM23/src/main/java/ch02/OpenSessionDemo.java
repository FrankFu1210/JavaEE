package ch02;

import org.hibernate.Session;

import ch00.HibernateUtils;

public class OpenSessionDemo {

	public static void main(String[] args) {
		Session session1 = HibernateUtils.getSessionFactory().openSession();
		Session session2 = HibernateUtils.getSessionFactory().openSession();
		System.out.println(session1 + ", hashCode=" + session1.hashCode());
		System.out.println(session2 + ", hashCode=" + session2.hashCode());
		boolean isEqual = session1.equals(session2);
		System.out.println("由SessionFactory的openSession()取出的兩個Session物件是否相等:" 
				+ isEqual);
	}
}
