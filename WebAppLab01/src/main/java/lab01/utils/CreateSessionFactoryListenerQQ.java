package lab01.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class CreateSessionFactoryListenerQQ implements ServletContextListener {

    public CreateSessionFactoryListenerQQ() {  
    	HibernateUtils.close();
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
         HibernateUtils.getSessionFactory();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
