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

    @GetMapping("/users")
    public String viewAllUsers(Model model) {
        List<User> userList = userService.findAllUsers();
        model.addAttribute("userList", userList);
        return "/users";
    }

    @GetMapping("/signup")
    public String addUser() {
        return "/signup";
    }

    @PostMapping("/signup")
    public String addUser(@RequestParam String firstName, @RequestParam String lastName) throws Exception {
        userService.createNewUser(firstName, lastName, null, null, null);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String userEdit(@RequestParam Integer id, Model uiModel) {
        this.id = id;
        User user = userService.findUserById(id);
        uiModel.addAttribute("user", user);
        return "/useredit";
    }

    @PostMapping("/edit")
    public String userEdit(@RequestParam String firstName, @RequestParam String lastName) {
        User user1 = userService.findUserById(id);
        user1.setFirstName(firstName);
        user1.setLastName(lastName);
        userService.updateUser(user1);
        return "redirect:/users";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
