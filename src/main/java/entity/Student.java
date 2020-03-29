package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Students")
public class Student extends User{
    @Column
    private int studyYear;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Students_Courses",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
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
