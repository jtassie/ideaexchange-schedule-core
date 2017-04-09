package org.ideaexchange.util;
 
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
 
public class HibernateUtil {
 
    private static SessionFactory sessionFactory;
 
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
             
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory();           
        }
         
        return sessionFactory;
    }
}