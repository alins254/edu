package business;

import Utilities.Konstants;
import Utilities.Utils;
import Utilities.Validators;
import entity.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import repository.AdminRepo;
import wrappers.AddUserWrapper;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class AdminService {
    //de verificat la addUser daca field-ul Account al obiectului User este gol
    AdminRepo adminRepo;

    public AdminService(){
        adminRepo = AdminRepo.getInstance();
    }

    @PostMapping("/addUser")
    public MessageBundle addUser(@RequestBody AddUserWrapper wrapper){
        String message = Validators.validateLogin(wrapper.username,wrapper.password);

        if(!message.equals(Konstants.VALID))
            return new MessageBundle(message,null);

        User user = Utils.createUser(wrapper.name, wrapper.dateOfBirth, new Account(wrapper.username, wrapper.password), wrapper.type);

        return adminRepo.addUser(user);
    }


    @PostMapping("/removeUser")
    public MessageBundle removeUser(@RequestBody String username){
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
