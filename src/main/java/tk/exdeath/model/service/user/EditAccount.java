package tk.exdeath.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.exdeath.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Service
public class EditAccount {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    public void updateUser(String password, String passwordConfirmation, String email) {
        if (!password.equals(passwordConfirmation)) {
            throw new RuntimeException("Passwords are not the same!");
        }
        String username = httpServletRequest.getRemoteUser();
        userService.updateUser(username, password, email);
    }
}
