package ru.kurma.service;

import ru.kurma.model.Role;
import ru.kurma.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> findAllUsers();
    User findUserById(Integer id);
    User findUserByLogin(String login);
    void createNewUser(String firstName, String lastName, String login, String password, Set<Role> role) throws Exception;
    void updateUser(User user);
    void deleteUser(Integer id);
}
