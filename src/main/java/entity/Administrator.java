package entity;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "Administrators")
public class Administrator extends User{
    public Administrator(){};

    public Administrator(String name, Date dateOfBirth, Account account){
        super(name, dateOfBirth, account);
    }
}
