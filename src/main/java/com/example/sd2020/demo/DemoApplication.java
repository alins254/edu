package com.example.sd2020.demo;

import business.LoginService;
import entity.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.AdminRepository;
import repository.LoginRepo;
import repository.StudentRepository;
import repository.TeacherRepo;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		AdminRepository adminRepository = AdminRepository.getInstance();
		TeacherRepo teacherRepo = TeacherRepo.getInstance();
		StudentRepository studentRepository = StudentRepository.getInstance();

		Account a1 = new Account("teach2","teach");
		User t1 = new Teacher("Ionel",new Date(),a1);
		a1.setUser(t1);
		adminRepository.addUser(t1);
		/*Account a3 = new Account("admin","admin");
		User us3 = new Administrator("Vasile",new Date(), a3);
		adminRepository.addUser(us3);*/
		Course c1 = new Course("PS2021","Password","PS1", new Date(), new Date(),(Teacher)t1);
		teacherRepo.addCourse(c1);
		Account a2 = new Account("stud2","stud");
		Student s2 = new Student("Ghita",new Date(),a2);
		a2.setUser(s2);
		adminRepository.addUser(s2);
		studentRepository.enrollStudent(s2, "PS2021", "Password");

		System.out.println(studentRepository.getStudentCourses(s2).size());


	}

}
