package com.example.sd2020.demo;

import Utilities.Konstants;
import Utilities.Utils;
import entity.Account;
import entity.User;
import org.junit.Before;
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
        User student = Utils.createUser("student",date, account, Konstants.T_STUDENT);
        assert(student.name.equals("student")&&student.dateOfBirth.equals(date)&&student.account.equals(account));
    }

}
