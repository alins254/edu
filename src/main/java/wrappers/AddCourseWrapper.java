package wrappers;

import entity.Teacher;

import java.util.Date;

public class AddCourseWrapper {
    public Teacher teacher;
    public String registerID;
    public String registerPassword;
    public String name;
    public Date startDate;
    public Date endDate;

    public AddCourseWrapper(Teacher teacher, String registerID, String registerPassword, String name, Date startDate, Date endDate) {
        this.teacher = teacher;
        this.registerID = registerID;
        this.registerPassword = registerPassword;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
