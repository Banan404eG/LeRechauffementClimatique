package tk.exdeath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tk.exdeath.model.entities.Role;
import tk.exdeath.model.entities.User;
import tk.exdeath.model.service.UserService;

import java.util.Collections;

@Controller
public class RegistrationController {

    private final String PATH = "registration";

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String returnPage() {
        return PATH;
    }

    @PostMapping("/registration")
    public String registration(User user, Model model) {
        try {
            user.setRoles(Collections.singleton(Role.USER));
            userService.create(user);
            model.addAttribute("users", userService.readAllUsers());
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
        }
        return PATH;
    }
}
