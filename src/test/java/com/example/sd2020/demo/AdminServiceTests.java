package com.example.sd2020.demo;

import Utilities.Konstants;
import Utilities.Utils;
import Utilities.Validators;
import business.AdminService;
import entity.Account;
import entity.MessageBundle;
import entity.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import repository.AdminRepo;
import wrappers.AddUserWrapper;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdminServiceTests {
    @Mock
    AdminRepo adminRepo;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private AdminService adminService;

    @Before
    public void init(){ adminService = new AdminService(adminRepo); };

    @Test
    public void testAddUserSuccessful(){
        AddUserWrapper wrapper = new AddUserWrapper("username", "password", "test", new Date(), Konstants.T_ADMIN);
        User expected = Utils.createUser(wrapper.name, wrapper.dateOfBirth, new Account(wrapper.username,wrapper.password),wrapper.type);
        when(adminRepo.addUser(any(User.class))).thenReturn(new MessageBundle(Konstants.SUCCESS,expected));

        MessageBundle messageBundle = adminService.addUser(wrapper);
        assert(messageBundle.message.equals(Konstants.SUCCESS));
        assert(((User)messageBundle.object).name.equals(expected.name));
        assert(((User)messageBundle.object).dateOfBirth == expected.dateOfBirth);
        assert(((User)messageBundle.object).account.getUsername().equals(expected.account.getUsername()));
        assert(((User)messageBundle.object).account.getPassword().equals(expected.account.getPassword()));

        verify(adminRepo).addUser(any(User.class));
    }

    @Test
    public void testAddUserDBError(){
        AddUserWrapper wrapper = new AddUserWrapper("username", "password", "test", new Date(), Konstants.T_ADMIN);
        when(adminRepo.addUser(any(User.class))).thenReturn(new MessageBundle(Konstants.USERNAME_EXISTS,null));

        MessageBundle messageBundle = adminService.addUser(wrapper);
        assert(messageBundle.message.equals(Konstants.USERNAME_EXISTS));
        assert(messageBundle.object == null);
        verify(adminRepo).addUser(any(User.class));
    }

    @Test
    public void testAddUserValidatorsError(){
        AddUserWrapper wrapper = new AddUserWrapper("u ", "p-", "test", new Date(), Konstants.T_ADMIN);

        MessageBundle messageBundle = adminService.addUser(wrapper);
        assert(messageBundle.message.equals(Validators.validateLogin("u ","p-")));
        assert(messageBundle.object == null);
    }

    @Test
    public void testRemoveUser(){
        when(adminRepo.removeUser("username")).thenReturn(new MessageBundle(Konstants.USERNAME_EXISTS,null));

        MessageBundle messageBundle = adminService.removeUser("username");
        assert(messageBundle.message.equals(Konstants.USERNAME_DOESNT_EXIST));
        assert messageBundle.object == null;
        verify(adminRepo).removeUser("username");
    }
}
