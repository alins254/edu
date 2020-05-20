package entity;

import Utilities.Konstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = Konstants.DB_TEACHER)
public class Teacher extends User {

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    List<Course> courses;

    public Teacher(){
        courses = new ArrayList<Course>();
    };

    public Teacher(String name, Date dateOfBirth, Account account) {
        super(name, dateOfBirth, account);
        courses = new ArrayList<Course>();
    }
}
