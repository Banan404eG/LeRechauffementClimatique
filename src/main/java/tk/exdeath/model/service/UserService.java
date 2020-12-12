package tk.exdeath.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.exdeath.model.entities.Role;
import tk.exdeath.model.entities.User;
import tk.exdeath.model.repos.UserRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${hostname}")
    private String hostname;

    public void create(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new RuntimeException("Login and password can't be null");
        }
        if (userRepo.readByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User with same login already exist");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new RuntimeException("Email can't be null");
        }

        user.setRoles(Collections.singleton(Role.USER));
        passwordEncoding(user);
        emailActivation(user);
        userRepo.save(user);
    }

    public List<User> readAllUsers() {
        return userRepo.findAll();
    }

    public User readUserByUsername(String username) {
        User user = userRepo.readByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public void updateUser(User user, String username, String[] roles) {
        user.setUsername(username);
        user.getRoles().clear();
        if (roles != null) {
            Arrays.stream(roles).forEach(r -> user.getRoles().add(Role.valueOf(r)));
        }
        userRepo.save(user);
    }

    public void updateUser(String username, String password, String email) {
        User user = userRepo.readByUsername(username);
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
            this.passwordEncoding(user);
        }
        if (!StringUtils.isEmpty(email)) {
            user.setEmail(email);
            this.emailActivation(user);
        }
        userRepo.save(user);
    }

    public void activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            throw new RuntimeException("Activation code is incorrect");
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);
    }

    private void passwordEncoding(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private void emailActivation(User user) {
        user.setActive(false);
        user.setActivationCode(UUID.randomUUID().toString());

        mailSender.sendMessage(user.getEmail(), "Activation code",
                "Hello, " + user.getUsername()
                        + "\n To activate your account, please follow the link: "
                        + "http://" + hostname + "/activate/" + user.getActivationCode());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.readByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
