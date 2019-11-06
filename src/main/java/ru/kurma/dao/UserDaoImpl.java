package ru.kurma.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kurma.model.Role;
import ru.kurma.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users;
        Query query = (Query) entityManager.createQuery("select e from User e");
        users = query.getResultList();
        return users;
    }

    @Override
    public User findUserById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByUserName(String username) {
        Query query = (Query) entityManager.createQuery("select e from User e where e.username = :username");
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Override
    @Transactional
    public void createNewUser(String firstName, String lastName, String username, String password, Set<Role> role) {
        entityManager.persist(new User(firstName, lastName, username, password, role));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        entityManager.remove(findUserById(id));
    }
}