package business;

import entity.Account;
import entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import repository.LoginRepo;

@RestController
public class LoginService {
    private LoginRepo loginRepo;

    public LoginService(LoginRepo loginRepo){
        this.loginRepo = loginRepo;
    }

    /**
     * Given an account it calls the repository method responsible
     * of retrieving the corresponding user from the database
     * @param account The user credentials
     * @return The user with the given credentials
     * if they can be found into the database
     * or a null reference if they cannot be found
     */
    @GetMapping("/attemptLogin") // localhost:8080/attemptLogin
    public User attemptLogin(@RequestBody Account account){
        User user = loginRepo.attemptLogin(account);
        if(user == null)
            System.out.println("Login attempt failed");
        return user;
    }
}
