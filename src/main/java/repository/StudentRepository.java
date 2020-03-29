package repository;

import Utilities.Konstants;
import entity.Course;
import entity.MessageBundle;
import entity.Student;

import javax.persistence.*;
import java.util.ArrayList;

public class StudentRepository implements StudentRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Konstants.DB_NAME);
    private static StudentRepository singletone = null;

    public static StudentRepository getInstance(){
        if(singletone == null)
            singletone = new StudentRepository();
        return singletone;
    }

    private StudentRepository(){}


    @Override
    public MessageBundle enrollStudent(Student student, String courseId, String coursePassword) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Student dbStudent = entityManager.find(Student.class, student.id);
        entityManager.getTransaction().commit();

        if(dbStudent == null){
            entityManager.close();
            return new MessageBundle(Konstants.STUDENT_DOESNT_EXIST,null);
        }

        entityManager.getTransaction().begin();
        Course dbCourse = entityManager.find(Course.class, courseId);
        entityManager.getTransaction().commit();

        if(dbCourse == null){
            entityManager.close();
            return new MessageBundle(Konstants.COURSE_DOESNT_EXIST,null);
        }

        if(!dbCourse.registerPassword.equals(coursePassword)){
            entityManager.close();
            return new MessageBundle(Konstants.COURSE_PASSWORD_WRONG,null);
        }
        student.courseList = getStudentCourses(student);
        student.courseList.add(dbCourse);
        entityManager.getTransaction().begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
        entityManager.close();
        return new MessageBundle(Konstants.SUCCESS, student);
    }


    public ArrayList<Course> getStudentCourses(Student student){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.println("SELECT c FROM Courses c join c.studentList s where s.id = \'"+student.id+"\'");
        Query query = entityManager.createQuery("SELECT c FROM Courses c join c.studentList s where s.id =\'"+student.id+"\'");
        entityManager.getTransaction().commit();
        ArrayList<Course> courses = (ArrayList<Course>) query.getResultList();
        entityManager.close();
        return courses;
    }
}
