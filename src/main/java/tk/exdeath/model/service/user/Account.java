package tk.exdeath.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.exdeath.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Service
public class Account {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    public String getUsername() {
        return httpServletRequest.getRemoteUser();
    }

    public boolean isAdmin() {
        return userService.readUserByUsername(httpServletRequest.getRemoteUser()).isAdmin();
    }
}
