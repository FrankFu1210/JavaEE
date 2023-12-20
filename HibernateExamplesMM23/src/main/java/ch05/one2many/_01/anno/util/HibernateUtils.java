//package ch05.one2many._01.anno.util;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.Metadata;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//
//public class HibernateUtils {
//
//	private static SessionFactory sessionFactory = buildSessionFactory();
////	private static ServiceRegistry serviceRegistry;
//	private static SessionFactory buildSessionFactory() {
//		try {
//			// Hibernate 5.x 的寫法: 採用addAnnotatedClass()加入永續類別
//			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
//	                .configure("ch05/one2many/_01/anno/util/hibernate.cfg.xml").build();
//			MetadataSources sources = new MetadataSources(standardRegistry);
//			Metadata metadata = sources.getMetadataBuilder().build();
//			SessionFactory sessionFactory  = metadata.getSessionFactoryBuilder().build();
//	        return sessionFactory; 
//			
//	        // Hibernate 5.x 的寫法: 在組態檔內定義永續類別
////			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
////	                .configure("ch01/hibernate.cfg.xml").build();
////	        Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
////	        SessionFactory sessionFactory  = metadata.getSessionFactoryBuilder().build();
////	        return sessionFactory;
//	        
//		} catch (Throwable ex) {
//			System.err.println("新建SessionFactory失敗:" + ex.getMessage());
//			ex.printStackTrace();
//			throw new ExceptionInInitializerError(ex);
//		}
//	}
//	// 外界呼叫此靜態方法來取的 SessionFactory物件
//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	public static void close() {
//		getSessionFactory().close();
//	}
//
//}