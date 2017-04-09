package org.ideaexchange.util;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
 
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
             
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory();           
        }
         
        return sessionFactory;
    }
    
    public static EntityManager getEntityManager(){
    	if (entityManager == null) { 
            // builds a session factory from the service registry
    		entityManager = Persistence.createEntityManagerFactory("org.ideaexchange").createEntityManager();          
        }
         
        return entityManager;
    }
}