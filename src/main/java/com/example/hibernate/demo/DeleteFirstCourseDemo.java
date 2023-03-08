package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteFirstCourseDemo {
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

            // get course
            int primaryKey = 10;
            Course firstCourse = session.get(Course.class, primaryKey);
            // delete the course
            System.out.println("Deleting course: " + firstCourse);
            session.delete(firstCourse);


            // commit transaction
            session.getTransaction().commit();
            System.out.println("Finito!");
        } finally {
            factory.close();
        }

    }
}
