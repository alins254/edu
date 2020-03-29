package repository;

import entity.Course;
import entity.MessageBundle;
import entity.Teacher;

import java.util.ArrayList;

public interface TeacherRepo {

    public static TeacherRepo getInstance(){
        return TeacherRepository.getInstance();
    }
    public ArrayList<Course> getTeacherCourses(Teacher teacher);
    public MessageBundle addCourse(Course course);
}
