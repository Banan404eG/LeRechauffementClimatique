package tk.exdeath.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.service.UserService;

@Controller
public class UserListController {

    private final String PATH = "admin/userList";

    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public String returnPage(Model model) {
        try {
            model.addAttribute("users", userService.readAllUsers());
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
        }
        return PATH;
    }

    @PostMapping("/userList")
    public String findByName(@RequestParam String username, Model model) {
        try {
            if (username.equals("")) {
                model.addAttribute("users", userService.readAllUsers());
            }
            model.addAttribute("users", userService.loadUserByUsername(username));
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
        }
        return PATH;
    }
}
