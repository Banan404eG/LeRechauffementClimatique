package tk.exdeath.model.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.exdeath.model.entities.Role;
import tk.exdeath.model.entities.User;
import tk.exdeath.model.service.UserService;

@Service
public class EditUser {

    @Autowired
    private UserService userService;

    public User getUserByUsername(String username) {
        return userService.readUserByUsername(username);
    }

    public Role[] getAllRoles() {
        return Role.values();
    }

    public void updateUser(String username, String newUsername, String[] roles) {
        User user = userService.readUserByUsername(username);
        userService.updateUser(user, newUsername, roles);
    }
}
