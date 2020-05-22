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
    public MessageBundle enrollStudent(String studentId, String courseId, String coursePassword) {
        MessageBundle messageBundle = validateEnrollment(studentId,courseId,coursePassword);
        if(!messageBundle.message.equals(Konstants.VALID))
            return messageBundle;

        Student dbStudent = (Student)messageBundle.object;
        Course dbCourse = (Course)messageBundle.extraObject;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        dbStudent.courseList = getStudentCourses(dbStudent.id);
        dbStudent.courseList.add(dbCourse);
        entityManager.getTransaction().begin();
        entityManager.merge(dbStudent);
        entityManager.getTransaction().commit();
        entityManager.close();

        return new MessageBundle(Konstants.SUCCESS, dbCourse);
    }

    MessageBundle validateEnrollment(String studentId, String courseId, String coursePassword){
        Student dbStudent;
        Course dbCourse;

        if((dbStudent = checkStudentId(studentId)) == null)
            return new MessageBundle(Konstants.STUDENT_DOESNT_EXIST,null);

        if((dbCourse = checkCourseId(courseId)) == null)
            return new MessageBundle(Konstants.COURSE_DOESNT_EXIST,null);

        if(!dbCourse.registerPassword.equals(coursePassword))
            return new MessageBundle(Konstants.COURSE_PASSWORD_WRONG,null);

        if(alreadyEnrolled(dbStudent.id,courseId))
            return new MessageBundle(Konstants.ALREADY_ENROLLED,null);

        MessageBundle messageBundle = new MessageBundle(Konstants.VALID, dbStudent);
        messageBundle.setExtraObject(dbCourse);
        return messageBundle;
    }


    public ArrayList<Course> getStudentCourses(String studentId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT c FROM Courses c join c.studentList s where s.id =\'"+studentId+"\'");
        entityManager.getTransaction().commit();
        ArrayList<Course> courses = (ArrayList<Course>) query.getResultList();
        entityManager.close();
        return courses;
    }

    Student checkStudentId(String studentId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Student dbStudent = entityManager.find(Student.class, studentId);
        entityManager.getTransaction().commit();

        entityManager.close();

        return dbStudent;
    }

    Course checkCourseId(String studentId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Course dbCourse = entityManager.find(Course.class, studentId);
        entityManager.getTransaction().commit();

        entityManager.close();

        return dbCourse;
    }

    boolean alreadyEnrolled(String studentId, String courseId){
        ArrayList<Course> courses = getStudentCourses(studentId);

        for(Course c: courses)
            if(c.registerID.equals(courseId)){
                return true;
            }

        return false;
    }
}
