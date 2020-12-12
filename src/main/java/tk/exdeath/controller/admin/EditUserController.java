package tk.exdeath.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.service.admin.EditUser;

@Controller
@RequestMapping("editUser")
@PreAuthorize("hasAuthority('ADMIN')")
public class EditUserController {

    private final String PATH = "admin/editUser";

    @Autowired
    private EditUser editUser;

    @GetMapping
    public String returnPage(@RequestParam String username, Model model) {
        try {
            model.addAttribute("user", editUser.getUserByUsername(username));
            model.addAttribute("roles", editUser.getAllRoles());
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
            editUser.updateUser(username, newUsername, roles);
            model.addAttribute("Message", newUsername + " successfully updated");
            model.addAttribute("user", editUser.getUserByUsername(newUsername));
            model.addAttribute("roles", editUser.getAllRoles());
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
        }
        return PATH;
    }
}
