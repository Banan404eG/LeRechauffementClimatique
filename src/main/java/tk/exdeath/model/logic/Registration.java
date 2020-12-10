package tk.exdeath.model.logic;

import org.springframework.beans.factory.annotation.Autowired;
import tk.exdeath.model.entities.User;
import tk.exdeath.model.service.UserService;

public class Registration {

    @Autowired
    private UserService userService;

    public void create(User user) {
        userService.create(user);
    }

    public void activateUser(String code) {
        userService.activateUser(code);
    }
}
