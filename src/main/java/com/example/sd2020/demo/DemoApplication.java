package com.example.sd2020.demo;

import business.LoginService;
import entity.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.AdminRepository;
import repository.LoginRepo;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		AdminRepository adminRepository = AdminRepository.getInstance();
		Account a1 = new Account("teach","teach");
		User t1 = new Teacher("Ion",new Date(),a1);
		a1.setUser(t1);
		adminRepository.addUser(t1);
		Account a2 = new Account("stud","stud");
		User s2 = new Student("Gheorghe",new Date(),a2);
		a2.setUser(s2);
		adminRepository.addUser(s2);
		Account a3 = new Account("admin","admin");
		User us3 = new Administrator("Vasile",new Date(), a3);
		adminRepository.addUser(us3);
		//SpringApplication.run(DemoApplication.class, args);
		//LoginService loginService = new LoginService(LoginRepo.getInstance());
		//User user = loginService.attemptLogin("teach1", "123");
		//System.out.println(user.name);
		//System.out.println("Teacher: "+ (user instanceof Teacher));
		//System.out.println("Student: "+ (user instanceof Student));
	}

}
