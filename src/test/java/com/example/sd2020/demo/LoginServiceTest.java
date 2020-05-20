package com.example.sd2020.demo;

import business.LoginService;
import entity.Account;
import entity.Teacher;
import entity.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import repository.LoginRepo;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginServiceTest {
    @Mock
    LoginRepo loginRepo;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private LoginService loginService;

    @Before
    public void init(){
        loginService = new LoginService(loginRepo);
    };

    @Test
    public void testAttemptLoginSuccessful(){
        Account account = new Account("abc","1234");
        User expected = new Teacher("Ion",new Date(),account);

        when(loginRepo.attemptLogin(account)).thenReturn(expected);
        User result = (User) loginService.attemptLogin(account).object;
        assertEquals(expected,result);
        verify(loginRepo).attemptLogin(account);
    }

    @Test
    public void testAttemptLoginError(){
        Account account = new Account("abc","1234");

        when(loginRepo.attemptLogin(account)).thenReturn(null);
        User result = (User) loginService.attemptLogin(account).object;
        assertEquals(null,result);
        verify(loginRepo).attemptLogin(account);
    }

}
