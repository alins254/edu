package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "user")
public abstract class User {

    @Id
    public final String id;
    @Column
    public String name;
    @Column
    public final Date dateOfBirth;

    @OneToOne
    @JoinColumn(name="username")
    public Account account;

    User(){
        dateOfBirth = new Date();
        id = UUID.randomUUID().toString();
        account = null;
    }

    User(String name, Date dateOfBirth, Account account) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.account = account;
        id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                dateOfBirth.equals(user.dateOfBirth) &&
                account.equals(user.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, account);
    }

    void update(Object obj){
        if(obj instanceof Student){
            System.out.println(((Student) obj).name+" joined the course!");
        }
    }
}