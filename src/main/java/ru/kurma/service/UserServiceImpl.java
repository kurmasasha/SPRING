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

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public User findUserByUserName(String login) {
        return userDao.findUserByUserName(login);
    }

    @Override
    public void createNewUser(String firstName, String lastName, String username, String password, Set<Role> role) throws Exception {
        userDao.createNewUser(firstName, lastName, username, password, role);
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
