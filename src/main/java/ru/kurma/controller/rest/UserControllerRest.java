package ru.kurma.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kurma.dao.RoleDao;
import ru.kurma.model.Role;
import ru.kurma.model.User;
import ru.kurma.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api")
public class UserControllerRest {

    private final UserService userService;

    private final RoleDao roleDao;


    @Autowired
    public UserControllerRest(UserService userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }


    @GetMapping("/user")
    public List<User> viewAdminPage() {
        return userService.findAllUsers();
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.findUserById(Integer.parseInt(id));
    }

    @PostMapping("/user")
    public User createNewUser(@RequestBody User user) throws Exception {

        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findRoleById(1));
        userService.createNewUser(user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getPassword(),
                roles);
        return user;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(Integer.parseInt(id));
    }
}
