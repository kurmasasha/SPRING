package ru.kurma.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kurma.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    @PersistenceContext
    //@Qualifier("entityManagerFactory")
    private EntityManager entityManager;
    //private SessionFactory sessionFactory;

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
    public User findUserByLogin(String login) {
        Query query = (Query) entityManager.createQuery("select e from User e where e.login = :login");
        query.setParameter("login", login);
        List<User> users = query.getResultList();
        User user = users.get(0);
        return user;
    }

    @Override
    @Transactional
    public void createNewUser(String firstName, String lastName, String login, String password, String role) throws Exception {
        entityManager.persist(new User(firstName, lastName, login, password, role));
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


//    @Override
//    public User findUserById(Integer id) {
//        User user;
//        Session session = sessionFactory.openSession();
//        user = session.get(User.class, id);
//        session.close();
//        return user;
//    }
//
//
//    @Override
//    public User findUserByLogin(String login) {
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery("From User where login = :login");
//        query.setParameter("login", login);
//        List<User> list = query.list();
//        if (list.size() == 0) {
//            return null;
//        }
//        else return list.get(0);
//    }
//
//    @Override
//    @Transactional
//    public void createNewUser(String firstName, String lastName, String login, String password, String role){
//        Session session = sessionFactory.openSession();
//        session.save(new User(firstName, lastName, login, password, role));
//        session.close();
//    }
//
//    @Override
//    @Transactional
//    public void updateUser(User user) {
//        Session session = sessionFactory.openSession();
//        session.update(user);
//        session.close();
//    }
//
//    @Override
//    @Transactional
//    public void deleteUser(Integer id) {
//        User user = findUserById(id);
//        Session session = sessionFactory.openSession();
//        session.delete(user);
//        session.close();
//    }
}