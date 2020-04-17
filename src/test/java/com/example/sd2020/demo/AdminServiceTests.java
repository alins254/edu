package com.example.sd2020.demo;

import business.AdminService;
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
import repository.AdminRepo;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdminServiceTests {
    @Mock
    AdminRepo adminRepo;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private AdminService adminService;

    @Before
    public void init(){
        adminService = new AdminService();
    };

    @Test
    public void testAddUserSuccessful(){

    }
}
