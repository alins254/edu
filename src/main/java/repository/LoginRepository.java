package repository;

import Utilities.Konstants;
import entity.Account;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class LoginRepository implements LoginRepo{
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Konstants.DB_NAME);
    private static LoginRepository singletone = null;

    private LoginRepository(){};

    public static LoginRepository getInstance(){
        if(singletone == null)
            singletone = new LoginRepository();
        return singletone;
    }


    @Override
    public User attemptLogin(Account account) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Account toFind = entityManager.find(Account.class, account.getUsername());
        entityManager.getTransaction().commit();

        if(toFind == null){
            entityManager.close();
            return null;
        }
        if(!toFind.getPassword().equals(account.getPassword())){
            entityManager.close();
            return null;
        }

        User user = searchUser(entityManager,toFind);
        entityManager.close();
        return user;
    }

    private User searchUser(EntityManager entityManager, Account founded){
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM Teachers t WHERE username = '" + founded.getUsername()+"'");
        entityManager.getTransaction().commit();
        if(!q.getResultList().isEmpty()){
            User teacher = (User) q.getResultList().get(0);
            if(teacher != null)
                return teacher;
        }

        entityManager.getTransaction().begin();
        q = entityManager.createQuery("SELECT s FROM Students s WHERE username = '" + founded.getUsername()+"'");
        entityManager.getTransaction().commit();
        if(!q.getResultList().isEmpty()) {
            User student = (User) q.getResultList().get(0);
            if (student != null)
                return student;
        }

        entityManager.getTransaction().begin();
        q = entityManager.createQuery("SELECT s FROM Administrators s WHERE username = '" + founded.getUsername()+"'");
        entityManager.getTransaction().commit();
        if(!q.getResultList().isEmpty()) {
            User admin = (User) q.getResultList().get(0);
            if (admin != null)
                return admin;
        }

        return null;
    }
}
