package business;

import entity.MessageBundle;
import entity.Student;
import repository.StudentRepo;

public class StudentService {
    private StudentRepo studentRepo;

    public StudentService(){
        studentRepo = StudentRepo.getInstace();
    }

    public MessageBundle enrollStudent(Student student, String courseId, String coursePassword){

        return studentRepo.enrollStudent(student,courseId,coursePassword);
    }
}
