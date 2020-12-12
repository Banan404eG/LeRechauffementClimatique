package tk.exdeath.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.exdeath.model.entities.User;
import tk.exdeath.model.service.UserService;

@Service
public class Registration {

    @Autowired
    private UserService userService;

    public void create(User user, String passwordConfirmation) {
        if (!user.getPassword().equals(passwordConfirmation)) {
            throw new RuntimeException("Passwords are not the same!");
        }
        userService.create(user);
    }

    public void activateUser(String code) {
        userService.activateUser(code);
    }
}
