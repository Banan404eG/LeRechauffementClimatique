package tk.exdeath.model.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.exdeath.model.entities.User;
import tk.exdeath.model.service.UserService;

import java.util.Collections;
import java.util.List;

@Service
public class UserList {

    @Autowired
    private UserService userService;

    public List<User> getAllUsers() {
        return userService.readAllUsers();
    }

    public List<User> getUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return getAllUsers();
        }
        return Collections.singletonList(userService.readUserByUsername(username));
    }
}
