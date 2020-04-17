package wrappers;

import java.util.Date;

public class AddUserWrapper {
    public String username;
    public String password;
    public String name;
    public Date dateOfBirth;
    public String type;

    public AddUserWrapper(String username, String password, String name, Date dateOfBirth, String type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
    }
}
