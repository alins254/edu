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

    @GetMapping("/attemptLogin") // localhost:8080/attemptLogin
    public User attemptLogin(@RequestBody Account account){
        Account toBeLoggedIn = new Account(account.getUsername(),account.getPassword());
        User user = loginRepo.attemptLogin(toBeLoggedIn);
        if(user == null)
            System.out.println("Login attempt failed");
        return user;
    }
}
