package org.example.util;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private  static String DB_HOST = "jdbc:mysql://localhost:3306/petclinic_java70";
    private static  String DB_USERNAME = "root";
    private static String DB_PASSWORD = "root";

    private  static SessionFactory sessionFactory = null;
    public static SessionFactory getSessionFactoryInstance(){
        if(sessionFactory == null){
            instantiateSessionFactory();
        }
        return sessionFactory;
    }
    private  static  void instantiateSessionFactory(){
        // boilerplate code
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.put(Environment.URL, DB_HOST);
        properties.put(Environment.USER, DB_USERNAME);
        properties.put(Environment.PASS, DB_PASSWORD);

        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.HBM2DDL_AUTO, "update");
        configuration.addAnnotatedClass(Vet.class);
        configuration.addAnnotatedClass(PetOwner.class);
        configuration.addAnnotatedClass(Pet.class);
        configuration.addAnnotatedClass(Appointment.class);
        configuration.addAnnotatedClass(Consult.class);


        configuration.setProperties(properties);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

}
