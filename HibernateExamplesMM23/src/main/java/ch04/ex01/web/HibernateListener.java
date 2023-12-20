//package ch04.ex01.web;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import ch00.HibernateUtils;
//
//@WebListener
//public class HibernateListener implements ServletContextListener {
//	public void contextDestroyed(ServletContextEvent arg0) {
//		HibernateUtils.close();
//	}
//
//	public void contextInitialized(ServletContextEvent arg0) {
//		new HibernateUtils();
//	}
//}