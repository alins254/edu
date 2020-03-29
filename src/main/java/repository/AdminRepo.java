package repository;

import entity.MessageBundle;
import entity.User;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.ArrayList;

public interface AdminRepo {
    public static AdminRepo getInstance(){
        return AdminRepository.getInstance();
    };

    public MessageBundle addUser(User user);

    public MessageBundle removeUser(String username);

    public ArrayList<User> listUsersWithType(User user);

    public ArrayList<User> listAllUsers();
}
