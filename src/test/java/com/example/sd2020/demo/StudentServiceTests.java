package com.example.sd2020.demo;

import Utilities.Konstants;
import Utilities.Utils;
import business.StudentService;
import business.TeacherService;
import entity.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import repository.StudentRepo;
import repository.TeacherRepo;
import wrappers.AddCourseWrapper;
import wrappers.EnrollStudentWrapper;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceTests {
    @Mock
    StudentRepo studentRepo;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private StudentService studentService;

    @Before
    public void init(){ studentService = new StudentService(studentRepo); };

    @Test
    public void testEnrollStudent(){
        Student student = (Student) Utils.createUser("abc",new Date(), new Account("username","password"), Konstants.T_STUDENT);
        EnrollStudentWrapper wrapper = new EnrollStudentWrapper(student.id,"ps2020_1","passwd");
        Course expected = new Course(wrapper.courseId,wrapper.coursePassword, "ps2020", new Date(), new Date(),null);
        when(studentRepo.enrollStudent(anyString(),anyString(),anyString())).thenReturn(new MessageBundle(Konstants.SUCCESS,expected));

        MessageBundle messageBundle = studentService.enrollStudent(wrapper);
        assert(messageBundle.message.equals(Konstants.SUCCESS));
        assert ((Course)messageBundle.object).registerID.equals(expected.registerID);
        assert ((Course)messageBundle.object).registerPassword.equals(expected.registerPassword);
        assert ((Course)messageBundle.object).name.equals(expected.name);
        assert ((Course)messageBundle.object).startDate == expected.startDate;
        assert ((Course)messageBundle.object).endDate == expected.endDate;

        verify(studentRepo).enrollStudent(anyString(),anyString(),anyString());
    }
}
