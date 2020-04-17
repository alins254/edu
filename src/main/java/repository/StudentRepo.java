package repository;

import entity.Course;
import entity.MessageBundle;
import entity.Student;

import java.util.ArrayList;

public interface StudentRepo {

    /**
     * It returns an instance of a StudentRepo object.
     *
     * @return an instance of a StudentRepo object
     */
    public static StudentRepo getInstace(){
        return StudentRepository.getInstance();
    }

    /**
     * Attempts to introduce the connection between
     * the student and the course into the database.
     * @param student the student which wish to enroll
     * @param courseId the course's id to which the student wishes to enroll
     * @param coursePassword the course's password to which the student wishes to enroll
     * @return a MessageBundle containing in the object field the course and the message "Success"
     * if the connection between the student and the course has been successfully added into the database,
     * or a null reference and the error message if the connection could not been established
     */
    public MessageBundle enrollStudent(Student student, String courseId, String coursePassword);

    /**
     * Retrieves all the student's courses from the database
     * @param student the student which courses are going to be retrieved
     * @return a list with the student's courses
     */
    public ArrayList<Course> getStudentCourses(Student student);

}
