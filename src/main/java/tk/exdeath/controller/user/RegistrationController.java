package tk.exdeath.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tk.exdeath.model.entities.User;
import tk.exdeath.model.service.user.Registration;

@Controller
public class RegistrationController {

    final String PATH = "user/registration";

    @Autowired
    private Registration registration;

    @GetMapping("registration")
    public String returnPage() {
        return "registration";
    }

    @PostMapping("registration")
    public String registration(User user, String passwordConfirmation, Model model) {
        try {
            registration.create(user, passwordConfirmation);
            model.addAttribute("Message", "Account successfully created, please check your mail");
            return "login";
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
            return PATH;
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        try {
            registration.activateUser(code);
            model.addAttribute("Message", "User successfully activated");
        } catch (Exception ex) {
            model.addAttribute("Message", ex.getMessage());
        }
        return "login";
    }
}
