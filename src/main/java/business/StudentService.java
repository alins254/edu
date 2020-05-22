package business;

import entity.Course;
import entity.MessageBundle;
import repository.StudentRepo;
import wrappers.EnrollStudentWrapper;

public class StudentService {
    private StudentRepo studentRepo;

    public StudentService(){
        studentRepo = StudentRepo.getInstace();
    }

    public StudentService(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }

    /**
     * Calls the repository method responsible for enrolling the student to a course
     * and adds the student into the course's list of students
     * @param wrapper containing the data necessary to attempt the enrollment process
     * @return a MessageBundle containing in the return value of the repository method
     */
    public MessageBundle enrollStudent(EnrollStudentWrapper wrapper){
        return studentRepo.enrollStudent(wrapper.studentId,wrapper.courseId,wrapper.coursePassword);
    }
}
