package Utilities;

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
}
