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

@RestController
public class AdminService {
    AdminRepo adminRepo;

    public AdminService(){
        adminRepo = AdminRepo.getInstance();
    }

    public AdminService(AdminRepo adminRepo){
        this.adminRepo = adminRepo;
    }

    /**
     * Given an AddUserWrapper object it validates the data
     * and then calls the repository method which will attempt
     * to introduce a new user and his account into the database
     * @param wrapper its a wrapper of all the data need in order to add the user into the database
     * @return a MessageBundle containing in the object field the user object and the message "Success"
     * if the user has been successfully added into the database, or a null reference and the error message
     * if the user has not been added into the database
     */
    @PostMapping("/addUser")
    public MessageBundle addUser(@RequestBody AddUserWrapper wrapper){
        String message = Validators.validateLogin(wrapper.username,wrapper.password);

        if(!message.equals(Konstants.VALID))
            return new MessageBundle(message,null);

        User user = Utils.createUser(wrapper.name, wrapper.dateOfBirth, new Account(wrapper.username, wrapper.password), wrapper.type);

        return adminRepo.addUser(user);
    }

    /**
     * Given an username verify if it is valid and then calls the repository
     * method responsible for eliminating the user with the given username.
     * The method will also remove the user's account from the database.
     * @param username the username of the user which have to be removed
     * @return a MessageBundle containing in the object field a null reference and the message "Success"
     * if the user has been successfully removed into the database, or a null reference and the error message
     * if the user has not been removed into the database
     */
    @PostMapping("/removeUser")
    public MessageBundle removeUser(@RequestBody String username){
        String message = Validators.validateUsername(username);

        if(!message.equals(Konstants.VALID))
            return new MessageBundle(message,null);

        return adminRepo.removeUser(username);
    }

    /**
     * It gets all the users from the database
     * @return A list including all the users from the database
     */
    @GetMapping("/listAllUsers")
    public ArrayList<User> listAllUsers(){
        return adminRepo.listAllUsers();
    }

}
