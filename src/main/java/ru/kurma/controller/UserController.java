package ru.kurma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kurma.model.Role;
import ru.kurma.model.User;
import ru.kurma.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private Integer id;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "redirect:/home";
    }

    @GetMapping("/admin/users")
    public String viewAllUsers(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        return "/admin/users";
    }

    @GetMapping("/signup")
    public String addUser(Authentication authentication) {
//        if (authentication != null) {
//            return "redirect:/";
//        }
        return "/login/signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String login,
                          @RequestParam String password) throws Exception {

        Set<Role> role = new HashSet<>();
        role.add(new Role("user"));
       // try {
            userService.createNewUser(firstName, lastName, login, password, role);
            return "redirect:/home";
        //} catch (Exception e) {
            //return "/login/errorsignup";
        //}

    }

    @GetMapping("/signin")
    public String signIn(Authentication authentication, Model model, HttpServletRequest request) {
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        if (authentication != null) {
            return "redirect:/";
        }
        return "/login/signin";
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
        Set<Role> roles = new HashSet<>();

        roles.add(new Role(role));
        user1.setRoles(roles);
        userService.updateUser(user1);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/home")
    public String home(Authentication authentication) {

        List<GrantedAuthority> autoritis = (List<GrantedAuthority>) authentication.getAuthorities();
        if("admin".equals(autoritis.get(0).getAuthority())) {
            return "redirect:/admin/adminhome";
        }
        return "/user/home";
    }

    @GetMapping("/admin/adminhome")
    public String adminHome() {
        return "/admin/adminhome";
    }
}
