package wrappers;

import entity.Student;

public class EnrollStudentWrapper {
    public String studentId;
    public String courseId;
    public String coursePassword;

    public EnrollStudentWrapper(String studentId, String courseId, String coursePassword) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.coursePassword = coursePassword;
    }
}
