package Utilities;

import entity.*;

import java.util.Date;

public class Utils {

    public static User createUser(String name, Date dateOfBirth, Account account, String type){
        switch (type){
            case Konstants.T_TEACHER: return new Teacher(name,dateOfBirth,account);
            case Konstants.T_STUDENT: return new Student(name,dateOfBirth,account);
            case Konstants.T_ADMIN: return new Administrator(name,dateOfBirth,account);
            default: return null;

        }
    }

}
