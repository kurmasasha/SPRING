package ru.kurma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kurma.dao.RoleDao;
import ru.kurma.model.Role;
import ru.kurma.model.User;
import ru.kurma.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;

    private final RoleDao roleDao;

    @Autowired
    public UserController(UserService userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping("")
    public String getHomePage() {
        return "redirect:/signin";
    }

    @GetMapping("/admin/**")
    public String viewAdminPage(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        return "admin";
    }

    @GetMapping("/user/**")
    public String viewUserPage() {
        return "user";
    }


    @PostMapping("/signup")
    public String signUp(@RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String login,
                          @RequestParam String password) {

        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findRoleById(1));
        try {
            userService.createNewUser(firstName, lastName, login, password, roles);
            return "redirect:/admin";
        } catch (Exception e) {
            return "/login/errorsignup";
        }
    }

    @GetMapping("/signin")
    public String signIn(Model model, HttpServletRequest request) {
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("er", true);
        }
        return "/login/signin";
    }

    @PostMapping("/admin/edit")
    public String userEdit(@RequestParam String id, @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String role) {
        User user1 = userService.findUserById(Integer.parseInt(id));
        user1.setFirstName(firstName);
        user1.setLastName(lastName);
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findRoleById(Integer.parseInt(role)));
        user1.setRoles(roles);
        userService.updateUser(user1);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
