package wrappers;

import java.io.Serializable;
import java.util.Date;

public class ListUserWrapper implements Serializable {
    public String username;
    public String name;
    public Date dateOfBirth;
    public String accountType;

    public ListUserWrapper(String username, String name, Date dateOfBirth, String accountType) {
        this.username = username;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.accountType = accountType;
    }
}
