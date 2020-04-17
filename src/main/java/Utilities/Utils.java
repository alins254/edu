package Utilities;

import entity.*;

import java.util.Date;

public class Utils {

    /**
     * A factory method which create an user object with the given type
     * @param name the name of the user
     * @param dateOfBirth the date of birth of the user
     * @param account the credentials linked to the user
     * @param type the type of the user which is going to be created
     * @return a user object with the specified type
     */
    public static User createUser(String name, Date dateOfBirth, Account account, String type){
        switch (type){
            case Konstants.T_TEACHER: return new Teacher(name,dateOfBirth,account);
            case Konstants.T_STUDENT: return new Student(name,dateOfBirth,account);
            case Konstants.T_ADMIN: return new Administrator(name,dateOfBirth,account);
            default: return null;

        }
    }

}
