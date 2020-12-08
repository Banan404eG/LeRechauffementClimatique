package tk.exdeath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tk.exdeath.model.entities.Role;
import tk.exdeath.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    @GetMapping("account")
    public String returnPage(Model model) {
        model.addAttribute("user", httpServletRequest.getRemoteUser());
        model.addAttribute("admin", userService.readUserByUsername(httpServletRequest.getRemoteUser()).getRoles().contains(Role.ADMIN));
        return "account";
    }
}
