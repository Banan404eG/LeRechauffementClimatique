package tk.exdeath.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.service.admin.UserList;

@Controller
@RequestMapping("userList")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserListController {

    private final String PATH = "admin/userList";

    @Autowired
    private UserList userList;

    @GetMapping
    public String returnPage(Model model) {
        try {
            model.addAttribute("users", userList.getAllUsers());
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
        }
        return PATH;
    }

    @PostMapping
    public String findByName(@RequestParam String username, Model model) {
        try {
            model.addAttribute("users", userList.getUserByUsername(username));
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
        }
        return PATH;
    }
}
