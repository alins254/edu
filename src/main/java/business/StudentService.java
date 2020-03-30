package business;

import entity.Course;
import entity.MessageBundle;
import entity.Student;
import repository.StudentRepo;

public class StudentService {
    private StudentRepo studentRepo;

    public StudentService(){
        studentRepo = StudentRepo.getInstace();
    }

    public MessageBundle enrollStudent(Student student, String courseId, String coursePassword){
        MessageBundle messageBundle = studentRepo.enrollStudent(student,courseId,coursePassword);
        if(messageBundle.object==null){
            Course course = (Course)messageBundle.object;
            course.addStudent(student);
        }
        return messageBundle;
    }
}
