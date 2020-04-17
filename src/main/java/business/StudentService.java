package business;

import entity.Course;
import entity.MessageBundle;
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

    public StudentService(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }

    /**
     * Calls the repository method responsible for enrolling the student to a course
     * and adds the student into the course's list of students
     * @param wrapper containing the data necessary to attempt the enrollment process
     * @return a MessageBundle containing in the return value of the repository method
     */
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
