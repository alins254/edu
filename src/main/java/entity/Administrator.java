package entity;

import Utilities.Konstants;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = Konstants.DB_ADMIN)
public class Administrator extends User{
    public Administrator(){};

    public Administrator(String name, Date dateOfBirth, Account account){
        super(name, dateOfBirth, account);
    }
}
