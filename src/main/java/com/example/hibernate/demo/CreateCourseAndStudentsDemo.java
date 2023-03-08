package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {
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

            // create a course
            Course theCourse = new Course("How to block in Street Fighter - The Basics");
            // save the course
            System.out.println("\nSaving the course");
            session.save(theCourse);
            System.out.println("\nSaved course: " + theCourse);

            // create students
            Student studentA = new Student("Lars", "Alexandersson", "lars@tekken.com");
            Student studentB = new Student("Nina", "Williams", "nina@tekken.com");
            Student studentC = new Student("Kazuya", "Mishima", "kazuya@tekken.com");
            // add students to course
            theCourse.addStudent(studentA);
            theCourse.addStudent(studentB);
            theCourse.addStudent(studentC);
            // save students
            session.save(studentA);
            session.save(studentB);
            session.save(studentC);
            System.out.println("Saved students: " + theCourse.getStudents());

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Finito!");
        } finally {
            factory.close();
        }

    }
}
