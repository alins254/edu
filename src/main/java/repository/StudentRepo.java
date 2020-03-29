package repository;

import entity.Course;
import entity.MessageBundle;
import entity.Student;

import java.util.ArrayList;

public interface StudentRepo {

    public static StudentRepo getInstace(){
        return StudentRepository.getInstance();
    }

    public MessageBundle enrollStudent(Student student, String courseId, String coursePassword);

    public ArrayList<Course> getStudentCourses(Student student);

}
