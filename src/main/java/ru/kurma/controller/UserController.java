package ru.kurma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kurma.model.User;

@RequestMapping("/users")
@Controller
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String userHandler(Model uiModel) {
        User user = new User();
        user.setFirstName("Sasha");
        uiModel.addAttribute("user", user);
        return "/users";
    }

}
