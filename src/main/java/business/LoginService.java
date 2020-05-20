package business;

import Utilities.Utils;
import entity.Account;
import entity.MessageBundle;
import entity.User;
import repository.LoginRepo;

public class LoginService {
    private LoginRepo loginRepo;

    public LoginService(LoginRepo loginRepo){
        this.loginRepo = loginRepo;
    }

    /**
     * Given an account it calls the repository method responsible
     * of retrieving the corresponding user from the database
     * @param account The user credentials
     * @return MessageBundle containing
     * The user with the given credentials and type
     * if they can be found into the database
     * or a null reference if they cannot be found
     */
    public MessageBundle attemptLogin(Account account){
        User user = loginRepo.attemptLogin(account);
        if(user == null)
            return new MessageBundle("Incorrect username or password", null);
        return new MessageBundle(Utils.getUserType(user),user);
    }
}
