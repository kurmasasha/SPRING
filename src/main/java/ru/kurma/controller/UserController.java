package ru.kurma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kurma.model.User;
import ru.kurma.service.UserService;

import java.util.List;

@RequestMapping("/")
@Controller
public class UserController {

    private Integer id;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public String viewAllUsers(Model model) {
        List<User> userList = userService.findAllUsers();
        model.addAttribute("userList", userList);
        return "/admin/users";
    }

    @GetMapping("/signup")
    public String addUser() {
        return "/login/signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String login,
                          @RequestParam String password) {
        try {
            userService.createNewUser(firstName, lastName, login, password, "user");
            return "redirect:/home";
        } catch (Exception e) {
            return "/login/errorsignup";
        }

    }

    @GetMapping("/signin")
    public String signIn() {
        return "/login/signin";
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam String login, @RequestParam String password) {
        User user = userService.findUserByLogin(login);
        if (user == null) {
            return "redirect:/signin";
        }
        if ((user.getLogin().equals(login)) && (user.getPassword().equals(password))) {
            return "redirect:/home";
        }
        else return "redirect:/signin";
    }

    @GetMapping("/admin/edit")
    public String userEdit(@RequestParam Integer id, Model uiModel) {
        this.id = id;
        User user = userService.findUserById(id);
        uiModel.addAttribute("user", user);
        return "/admin/useredit";
    }

    @PostMapping("/admin/edit")
    public String userEdit(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String role) {
        User user1 = userService.findUserById(id);
        user1.setFirstName(firstName);
        user1.setLastName(lastName);
        user1.setRole(role);
        userService.updateUser(user1);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/home")
    public String home() {
        return "/user/home";
    }

//    @GetMapping("/admin/adminhome")
//    public String adminHome() {
//        return "admin/adminhome";
//    }
}
