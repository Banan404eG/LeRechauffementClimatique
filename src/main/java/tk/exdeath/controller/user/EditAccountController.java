package tk.exdeath.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tk.exdeath.model.service.user.EditAccount;

@Controller
@RequestMapping("editAccount")
public class EditAccountController {

    final String PATH = "user/editAccount";

    @Autowired
    private EditAccount editAccount;

    @GetMapping
    public String returnPage() {
        return PATH;
    }

    @PostMapping
    public String updateAccount(
            @RequestParam String password,
            @RequestParam String passwordConfirmation,
            @RequestParam String email, Model model) {
        try {
            editAccount.updateUser(password, passwordConfirmation, email);
            model.addAttribute("Message", "Your account successfully updated");
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
        }
        return PATH;
    }
}
