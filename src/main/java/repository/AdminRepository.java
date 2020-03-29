package repository;

import Utilities.Konstants;
import entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;

public class AdminRepository implements AdminRepo{
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Konstants.DB_NAME);
    private static AdminRepository singletone = null;

    private AdminRepository(){};

    public static AdminRepository getInstance(){
        if(singletone == null)
            singletone = new AdminRepository();
        return singletone;
    }

    public MessageBundle addUser(User user){
        /**
         * Pentru fiecare metoda se instantiaza un entiy manager din EMF-ul de mai sus.
         */
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        /**
         * Denota ca incepe o operatie pe baza de date
         */
        entityManager.getTransaction().begin();
        /**
         * Declaram operatia sau operatiile ce se vor face pe BD.
         */
        Account existingAccount = entityManager.find(Account.class, user.account.getUsername());
        if(existingAccount != null){
            entityManager.getTransaction().commit();
            entityManager.close();
            return new MessageBundle(Konstants.USERNAME_EXISTS, null);
        }
        entityManager.merge(user.account);
        entityManager.merge(user);
        /**
         * Executam operatia.
         */
        entityManager.getTransaction().commit();
        /**
         * Inchidem EM-ul. Ca la operatii pe fisiere, il deschizi, il citesti si in final il inchizi.
         */
        entityManager.close();
        return new MessageBundle(Konstants.SUCCESS, user);
    }

    public MessageBundle removeUser(String username){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Account account = em.find(Account.class, username);
        if(account == null){
            em.close();
            return new MessageBundle(Konstants.USERNAME_DOESNT_EXIST,null);
        }
        em.remove(account);
        em.getTransaction().commit();
        em.close();
        return new MessageBundle(Konstants.SUCCESS,null);
    }

    public ArrayList<User> listAllUsers(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        ArrayList<User> userArrayList = listUsersWithType(new Administrator());
        userArrayList.addAll(listUsersWithType(new Teacher()));
        userArrayList.addAll(listUsersWithType(new Student()));
        em.getTransaction().commit();
        em.close();
        return userArrayList;
    }

    public ArrayList<User> listUsersWithType(User user){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM " + User.class.getSimpleName() + "s p");
        em.getTransaction().commit();
        ArrayList<User> users = (ArrayList<User>) query.getResultList();
        em.close();
        return users;
    }
}
