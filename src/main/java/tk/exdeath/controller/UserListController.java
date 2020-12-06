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
public class UserListController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String returnPage() {
        return "test";
    }

    @PostMapping("/test")
    public String test(User user, Model model) {
        user.setRoles(Collections.singleton(Role.USER));
        userService.create(user);
        model.addAttribute("users", userService.readAllUsers());
        return "test";
    }
}
