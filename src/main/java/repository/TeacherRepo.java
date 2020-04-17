package repository;

import entity.Course;
import entity.MessageBundle;
import entity.Teacher;

import java.util.ArrayList;

public interface TeacherRepo {
    /**
     * It returns an instance of a TeacherRepo object.
     *
     * @return an instance of a TeacherRepo object
     */
    public static TeacherRepo getInstance(){
        return TeacherRepository.getInstance();
    }

    /**
     * Attempts to add a course into the database
     * @param course the course to be added into the database
     * @return a MessageBundle containing in the object field the course and the message "Success"
     * if the course has been successfully added into the database,
     * or a null reference and the error message if course could not be added
     */
    public MessageBundle addCourse(Course course);

    /**
     * Retrieves all the teacher's courses from the database
     * @param teacher the student which courses are going to be retrieved
     * @return a list with the teacher's courses
     */
    public ArrayList<Course> getTeacherCourses(Teacher teacher);
}
