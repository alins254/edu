package business;

import Utilities.Konstants;
import Utilities.Validators;
import entity.Course;
import entity.MessageBundle;
import entity.Teacher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import repository.TeacherRepo;
import wrappers.AddCourseWrapper;

import java.util.Date;

@RestController
public class TeacherService {
    private TeacherRepo teacherRepo;

    public TeacherService(){
        teacherRepo = TeacherRepo.getInstance();
    }

    @PostMapping("/addCourse")
    public MessageBundle addCourse(@RequestBody AddCourseWrapper wrapper){
        if(wrapper.teacher == null)
            return new MessageBundle(Konstants.TEACHER_DOESNT_EXIST,null);

        String message = Validators.validateCourse(wrapper.registerID,wrapper.registerPassword,wrapper.name,wrapper.startDate,wrapper.endDate);

        if(!message.equals(Konstants.VALID))
            return new MessageBundle(message, null);

        MessageBundle messageBundle = teacherRepo.addCourse(new Course(wrapper.registerID,wrapper.registerPassword,
                wrapper.name,wrapper.startDate,wrapper.endDate,wrapper.teacher));
        if(messageBundle.object!=null){
            Course course = (Course)messageBundle.object;
            course.addObserver(wrapper.teacher);
        }
        return messageBundle;
    }
}
