package business;

import entity.Course;
import entity.MessageBundle;
import entity.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import repository.StudentRepo;
import wrappers.EnrollStudentWrapper;

@RestController
public class StudentService {
    private StudentRepo studentRepo;

    public StudentService(){
        studentRepo = StudentRepo.getInstace();
    }

    @PostMapping("/enrollStudent")
    public MessageBundle enrollStudent(@RequestBody EnrollStudentWrapper wrapper){
        MessageBundle messageBundle = studentRepo.enrollStudent(wrapper.student,wrapper.courseId,wrapper.coursePassword);
        if(messageBundle.object!=null){
            Course course = (Course)messageBundle.object;
            course.addStudent(wrapper.student);
        }
        return messageBundle;
    }
}
