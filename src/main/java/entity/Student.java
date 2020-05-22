package entity;

import Utilities.Konstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = Konstants.DB_STUDENT)
public class Student extends User{
    @Column
    private int studyYear;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Students_Courses",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    @JsonIgnore
    public List<Course> courseList = new ArrayList<Course>();

    public Student(){};

    public Student(String name, Date dateOfBirth, Account account){
        super(name, dateOfBirth, account);
        studyYear = 1;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }
}
