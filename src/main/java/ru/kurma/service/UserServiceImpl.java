package ru.kurma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kurma.dao.UserDao;
import ru.kurma.model.Role;
import ru.kurma.model.User;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public User findUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    @Override
    public void createNewUser(String firstName, String lastName, String login, String password, Set<Role> role) throws Exception {
        userDao.createNewUser(firstName, lastName, login, password, role);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }
}
