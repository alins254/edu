package business;

import Utilities.Konstants;
import Utilities.Validators;
import entity.Course;
import entity.MessageBundle;
import entity.Teacher;
import repository.TeacherRepo;

import java.util.Date;

public class TeacherService {
    private TeacherRepo teacherRepo;

    public TeacherService(){
        teacherRepo = TeacherRepo.getInstance();
    }

    public MessageBundle addCourse(Teacher teacher, String registerID, String registerPassword, String name, Date startDate, Date endDate){
        if(teacher == null)
            return new MessageBundle(Konstants.TEACHER_DOESNT_EXIST,null);

        String message = Validators.validateCourse(registerID,registerPassword,name,startDate,endDate);

        if(!message.equals(Konstants.VALID))
            return new MessageBundle(message, null);

        MessageBundle messageBundle = teacherRepo.addCourse(new Course(registerID,registerPassword,name,startDate,endDate,teacher));
        if(messageBundle.object!=null){
            Course course = (Course)messageBundle.object;
            course.addObserver(teacher);
        }
        return messageBundle;
    }
}
