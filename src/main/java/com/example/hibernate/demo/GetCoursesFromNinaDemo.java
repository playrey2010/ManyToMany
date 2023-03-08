package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesFromNinaDemo {
    public static void main(String[] args) {

        // create session factory

        SessionFactory factory = new Configuration()
                .configure("/hibernate.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                addAnnotatedClass(Course.class).
                addAnnotatedClass(Review.class).
                addAnnotatedClass(Student.class).
                buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get nina williams from database
            int primaryKey = 2;
            Student student = session.get(Student.class, primaryKey);
            System.out.println("\nLoaded student: " + student);
            System.out.println("Current courses: " + student.getCourses());
            // commit transaction
            session.getTransaction().commit();
            System.out.println("Finito!");
        } finally {
            factory.close();
        }

    }
}
