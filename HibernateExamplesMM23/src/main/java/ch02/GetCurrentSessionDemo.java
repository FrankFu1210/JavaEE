package ch02;

import org.hibernate.Session;

import ch00.HibernateUtils;

public class GetCurrentSessionDemo {

	public static void main(String[] args) {
		Session session1 = HibernateUtils.getSessionFactory().getCurrentSession();
		Session session2 = HibernateUtils.getSessionFactory().getCurrentSession();
		System.out.println(session1 + ", hashCode=" + session1.hashCode());
		System.out.println(session2 + ", hashCode=" + session2.hashCode());
		boolean isEqual = session1.equals(session2);
		System.out.println("由SessionFactory的getCurrentSession()取出的兩個Session物件是否相等:" 
			+ isEqual);
	}
}
