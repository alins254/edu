package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name="Courses")
public class Course {
    @Id
    public final String registerID;

    @Column
    public String registerPassword;

    @Column
    public String name;

    @Column
    public Date startDate;

    @Column
    public Date endDate;

    @ManyToOne
    @JoinColumn(name="teacher_ID")
    Teacher teacher;

    @ManyToMany(mappedBy = "courseList")
    private List<Student> studentList = new ArrayList<Student>();

    @Transient
    private List<User> observers = new ArrayList<User>();


    public Course() {
        this.registerID = UUID.randomUUID().toString();
        teacher = null;
    }

    public Course(String registerID, String registerPassword, String name, Date startDate, Date endDate, Teacher teacher) {
        this.registerID = registerID;
        this.registerPassword = registerPassword;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addObserver(User user) {
        this.observers.add(user);
    }

    public void removeObserver(User user) {
        this.observers.remove(user);
    }

    public List<User> getObservers() {
        return observers;
    }

    public void addStudent(Student student) {
        studentList.add(student);
        for (User user : this.observers) {
            user.update(student);
        }
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
