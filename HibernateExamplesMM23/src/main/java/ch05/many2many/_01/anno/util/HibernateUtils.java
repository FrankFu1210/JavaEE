//package ch05.many2many._01.anno.util;
// 
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.Metadata;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//
// 
//public class HibernateUtils {
// 
//	private static final SessionFactory sessionFactory = buildSessionFactory();
// 
//	private static SessionFactory buildSessionFactory() {
//		try {
//			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
//	                .configure("ch05/many2many/_01/anno/util/hibernate.cfg.xml").build();
//			MetadataSources sources = new MetadataSources(standardRegistry);
//			Metadata metadata = sources.getMetadataBuilder().build();
//			SessionFactory sessionFactory  = metadata.getSessionFactoryBuilder().build();
//	        return sessionFactory; 
//		} catch (Throwable ex) {
//			// Make sure you log the exception, as it might be swallowed
//			System.err.println("Initial SessionFactory creation failed." + ex);
//			throw new ExceptionInInitializerError(ex);
//		}
//	}
// 
//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
// 
//	public static void close() {
//		// Close caches and connection pools
//		getSessionFactory().close();
//	}
// 
//}