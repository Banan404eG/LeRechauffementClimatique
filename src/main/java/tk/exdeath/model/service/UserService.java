package tk.exdeath.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.exdeath.model.entities.Role;
import tk.exdeath.model.entities.User;
import tk.exdeath.model.repos.UserRepo;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void create(String username, String password, Set<Role> roles) {
        if (username == null || password == null) {
            throw new RuntimeException("Login and password can't be null");
        }
        if (userRepo.readByUsername(username) != null) {
            throw new RuntimeException("User with same username already exist");
        }
        userRepo.save(new User(username, password, roles));
    }

    public void create(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new RuntimeException("Login and password can't be null");
        }
        if (userRepo.readByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User with same login already exist");
        }
        userRepo.save(user);
    }

    public User readByUsername(String username) {
        User user = userRepo.readByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public List<User> readAllUsers() {
        return userRepo.findAll();
    }
}
