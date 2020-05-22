package EduMain;

import Utilities.Konstants;
import Utilities.Utils;
import business.TeacherService;
import entity.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import repository.TeacherRepo;
import wrappers.AddCourseWrapper;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TeacherServiceTests {
    @Mock
    TeacherRepo teacherRepo;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private TeacherService teacherService;

    @Before
    public void init(){ teacherService = new TeacherService(teacherRepo); };

    @Test
    public void testAddCourse(){
        Teacher teacher = (Teacher)Utils.createUser("abc",new Date(), new Account("username","password"),Konstants.T_TEACHER);
        AddCourseWrapper wrapper = new AddCourseWrapper(teacher, "testid","testpass","PS2020",new Date(), new Date());
        Course expected = new Course(wrapper.registerID,wrapper.registerPassword,wrapper.name,wrapper.startDate,wrapper.endDate, teacher);
        when(teacherRepo.addCourse(any(Course.class))).thenReturn(new MessageBundle(Konstants.SUCCESS,expected));

        MessageBundle messageBundle = teacherService.addCourse(wrapper);
        assert(messageBundle.message.equals(Konstants.SUCCESS));
        assert ((Course)messageBundle.object).registerID.equals(expected.registerID);
        assert ((Course)messageBundle.object).registerPassword.equals(expected.registerPassword);
        assert ((Course)messageBundle.object).name.equals(expected.name);
        assert ((Course)messageBundle.object).startDate == expected.startDate;
        assert ((Course)messageBundle.object).endDate == expected.endDate;
        assert ((Course)messageBundle.object).getTeacher().equals(expected.getTeacher());
        assert ((Course)messageBundle.object).getObservers().contains(teacher);

        verify(teacherRepo).addCourse(any(Course.class));
    }
}
