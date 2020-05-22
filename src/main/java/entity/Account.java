package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "accounts")
public class Account{
    @Id
    private String username;
    @Column
    private String password;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private User user;

    public Account(){
    };

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public User getUser() {
        return user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username) &&
                Objects.equals(password, account.password) &&
                Objects.equals(user, account.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, user);
    }
}
