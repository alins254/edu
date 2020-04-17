package wrappers;

import entity.Student;

public class EnrollStudentWrapper {
    public Student student;
    public String courseId;
    public String coursePassword;

    public EnrollStudentWrapper(Student student, String courseId, String coursePassword) {
        this.student = student;
        this.courseId = courseId;
        this.coursePassword = coursePassword;
    }
}
