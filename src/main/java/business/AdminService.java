package business;

import Utilities.Konstants;
import Utilities.Utils;
import Utilities.Validators;
import entity.*;
import org.springframework.web.bind.annotation.GetMapping;
import repository.AdminRepo;

import java.util.ArrayList;
import java.util.Date;

public class AdminService {
    //de verificat la addUser daca field-ul Account al obiectului User este gol
    AdminRepo adminRepo;

    public AdminService(){
        adminRepo = AdminRepo.getInstance();
    }

    @GetMapping("/addUser")
    public MessageBundle addUser(String username, String password, String name, Date dateOfBirth, String type){
        String message = Validators.validateLogin(username,password);

        if(!message.equals(Konstants.VALID))
            return new MessageBundle(message,null);

        User user = Utils.createUser(name, dateOfBirth, new Account(username, password), type);

        return adminRepo.addUser(user);
    }


    @GetMapping("/removeUser")
    public MessageBundle removeUser(String username){
        String message = Validators.validateUsername(username);

        if(!message.equals(Konstants.VALID))
            return new MessageBundle(message,null);

        return adminRepo.removeUser(username);
    }

    @GetMapping("/listAllUsers")
    public ArrayList<User> listAllUsers(){
        return adminRepo.listAllUsers();
    }

}
