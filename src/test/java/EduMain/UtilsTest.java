package EduMain;

import Utilities.Konstants;
import Utilities.Utils;
import entity.*;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Date;

public class UtilsTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void utilsCreateUserStudent(){
        Date date = new Date();
        Account account = new Account("test1","test2");
        User user = Utils.createUser("student",date, account, Konstants.T_STUDENT);
        assert(user.name.equals("student")&&user.dateOfBirth.equals(date)&&user.account.equals(account)&&user instanceof Student);
    }

    @Test
    public void utilsCreateUserTeacher(){
        Date date = new Date();
        Account account = new Account("test1","test2");
        User user = Utils.createUser("teacher",date, account, Konstants.T_TEACHER);
        assert(user.name.equals("teacher")&&user.dateOfBirth.equals(date)&&user.account.equals(account)&&user instanceof Teacher);
    }

    @Test
    public void utilsCreateUserAdministrator(){
        Date date = new Date();
        Account account = new Account("test1","test2");
        User user = Utils.createUser("admin",date, account, Konstants.T_ADMIN);
        assert(user.name.equals("admin")&&user.dateOfBirth.equals(date)&&user.account.equals(account)&&user instanceof Administrator);
    }

}
