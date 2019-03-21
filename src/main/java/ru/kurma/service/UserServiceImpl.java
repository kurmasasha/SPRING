package ru.kurma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurma.dao.UserDao;
import ru.kurma.model.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    @Transactional
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional
    public User findUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    @Override
    @Transactional
    public void createNewUser(String firstName, String lastName, String login, String password, String role) throws Exception {
        userDao.createNewUser(firstName, lastName, login, password, role);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }
}
