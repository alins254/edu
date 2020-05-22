package repository;

import entity.MessageBundle;
import entity.User;

import java.util.ArrayList;

public interface AdminRepo {

    /**
     * It returns an instance of a AdminRepo object.
     *
     * @return an instance of a AdminRepo object
     */
    public static AdminRepo getInstance(){
        return AdminRepository.getInstance();
    };

    /**
     * Attempts to add a user into the database
     * @param user the user to be added into the database
     * @return a MessageBundle containing in the object field the user and the message "Success"
     * if the user has been successfully added into the database,
     * or a null reference and the error message if user could not be added
     */
    public MessageBundle addUser(User user);

    /**
     * Attempts to remove a user from the database
     * @param username the username corresponding to the user to be removed from the database
     * @return a MessageBundle containing in the object field a null reference and the message "Success"
     * if the user has been successfully removed into the database,
     * or a null reference and the error message if user could not be removed
     */
    public MessageBundle removeUser(String username);

    /**
     * Lists all the users with the given type from the database
     * @param table A reference to the database table with the object type representing the type which is going to be retrieved
     * @return the list of all the user with the given type from the database
     */
    public ArrayList<User> listUsersWithType(String table);

    /**
     * Lists all the users from the database
     * @return a list containing all the users found into the database
     */
    public ArrayList<User> listAllUsers();
}
