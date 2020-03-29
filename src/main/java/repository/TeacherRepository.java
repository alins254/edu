package repository;

import Utilities.Konstants;
import entity.Course;
import entity.MessageBundle;
import entity.Teacher;

import javax.persistence.*;
import java.util.ArrayList;

public class TeacherRepository implements TeacherRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Konstants.DB_NAME);
    private static TeacherRepository singletone = null;

    public static TeacherRepository getInstance(){
        if(singletone == null)
            singletone = new TeacherRepository();
        return singletone;
    }

    private TeacherRepository(){}


    @Override
    public ArrayList<Course> getTeacherCourses(Teacher teacher) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT c FROM Courses c WHERE teacher_ID = "+ teacher.id);
        em.getTransaction().commit();
        em.close();
        return (ArrayList<Course>) query.getResultList();
    }

    @Override
    public MessageBundle addCourse(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Teacher dbTeacher = entityManager.find(Teacher.class, course.getTeacher().id);
        entityManager.getTransaction().commit();

        if(dbTeacher == null){
            entityManager.close();
            return new MessageBundle(Konstants.TEACHER_DOESNT_EXIST, null);
        }

        entityManager.getTransaction().begin();
        Course dbCourse = entityManager.find(Course.class, course.registerID);
        entityManager.getTransaction().commit();

        if(dbCourse!=null){
            entityManager.close();
            return new MessageBundle(Konstants.COURSE_EXISTS, null);
        }

        entityManager.getTransaction().begin();
        entityManager.merge(course);
        entityManager.getTransaction().commit();
        entityManager.close();
        return new MessageBundle(Konstants.SUCCESS,course);
    }
}
