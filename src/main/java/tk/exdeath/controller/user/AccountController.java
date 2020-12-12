package tk.exdeath.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.service.user.Account;

@Controller
public class AccountController {

    final String PATH = "user/account";

    @Autowired
    private Account account;

    @GetMapping("account")
    public String returnPage(Model model) {
        model.addAttribute("username", account.getUsername());
        model.addAttribute("isAdmin", account.isAdmin());
        return PATH;
    }
}
