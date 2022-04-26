package com.andreagreco.StudentsLaptop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App 
{
    public static void main( String[] args )
    {
        Laptop l1 = new Laptop();
        l1.setLid(101);
        l1.setLname("Asus");
        
        Laptop l2 = new Laptop();
        l2.setLid(102);
        l2.setLname("MacBook");
        
        
        
        Student s1 = new Student();
        s1.setMarks(50);
        s1.setName("Andrea");
        s1.setRollno(1);
        
        Student s2 = new Student();
        s2.setMarks(51);
        s2.setName("Jim");
        s2.setRollno(2);
        
        
//        s1.setLaptop(lap);
        s1.getLaptop().add(l1);
        s1.getLaptop().add(l2);
        
        l1.getStudent().add(s1);
        l1.getStudent().add(s2);
        l2.getStudent().add(s1);
        
        Configuration con = new Configuration().configure()
        		.addAnnotatedClass(Student.class)
        		.addAnnotatedClass(Laptop.class);
        ServiceRegistry reg = new ServiceRegistryBuilder()
        		.applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(l1);
        session.save(l2);
        session.save(s1);
        session.save(s2);
        tx.commit();
 
    }
}
