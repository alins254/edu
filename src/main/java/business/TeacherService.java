package business;

import Utilities.Konstants;
import Utilities.Validators;
import entity.Course;
import entity.MessageBundle;
import repository.TeacherRepo;
import wrappers.AddCourseWrapper;

public class TeacherService {
    private TeacherRepo teacherRepo;

    public TeacherService(){
        teacherRepo = TeacherRepo.getInstance();
    }

    public TeacherService(TeacherRepo teacherRepo){
        this.teacherRepo = teacherRepo;
    }
    /**
     * Validate the received data and then calls the repository method
     * responsible for adding the course into the database.
     * The teacher will be added into the course's observer list
     * @param wrapper containing the data necessary for attempting the adding of the course into the database
     * @return a MessageBundle containing in the object field the course object and the message "Success"
     * if the course has been successfully added into the database, or a null reference and the error messages
     * if the course has not been successfully added into the database
     */
    public MessageBundle addCourse(AddCourseWrapper wrapper){
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
