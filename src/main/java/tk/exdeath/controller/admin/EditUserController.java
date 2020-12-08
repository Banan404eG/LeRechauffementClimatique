package tk.exdeath.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.entities.Role;
import tk.exdeath.model.entities.User;
import tk.exdeath.model.service.UserService;

@Controller
@RequestMapping("editUser")
@PreAuthorize("hasAuthority('ADMIN')")
public class EditUserController {

    private final String PATH = "admin/editUser";

    @Autowired
    private UserService userService;

    @GetMapping
    public String returnPage(@RequestParam String username, Model model) {
        try {
            model.addAttribute("user", userService.readUserByUsername(username));
            model.addAttribute("roles", Role.values());
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
        }
        return PATH;
    }

    @PostMapping
    public String updateUser(
            @RequestParam String newUsername,
            @RequestParam String username,
            @RequestParam(name = "roles[]", required = false) String[] roles, Model model) {
        try {
            User user = userService.readUserByUsername(username);
            userService.updateUser(user, newUsername, roles);
            model.addAttribute("Message", newUsername + " successfully updated");
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
        }
        return PATH;
    }

}
