package Utilities;

import java.util.Date;

public class Validators {

    public static String validateLogin(String username, String password){
        String messages = new String("");
        messages+=validateUsername(username);
        messages+=validatePassword(password);
        if(messages.length()>2) return messages;

        return Konstants.VALID;
    }

    public static String validateUsername(String username){
        String messages = new String("");
        if(username.length()<8) messages+="username to short - ";
        if(username.length()>30) messages+="username to long - ";
        if(username.contains(" "))  messages+="username cannot contain spaces - ";
        return messages;
    }

    public static String validatePassword(String password){
        String messages = new String("");
        if(password.contains(" ")) messages+="password cannot contain spaces - ";
        if(password.length()<8) messages+="password to short - ";
        if(password.length()>30) messages+="password to long - ";
        return messages;
    }

    public static String validateCourseRegisterID(String registerID){
        String messages = new String("");
        if(registerID.length()<6) messages+="registerID to short - ";
        if(registerID.length()>25) messages+="registerID to long - ";
        if(registerID.contains(" "))  messages+="registerID cannot contain spaces - ";
        return messages;
    }

    public static String validateCourse(String registerID, String registerPassword, String name, Date startDate, Date endDate){
        if(endDate == null || startDate == null || registerID == null || registerPassword == null || name == null)
            return Konstants.ERR;

        String message = new String("");
        message+=validateCourseRegisterID(registerID);
        message+=validatePassword(registerPassword);

        if(startDate.getTime()>endDate.getTime())
            message+="Start date before end date - ";
        if(name.length()<5)
            message+="Course name to short - ";
        if(message.length()>1)
            return message;

        return Konstants.VALID;
    }
}
