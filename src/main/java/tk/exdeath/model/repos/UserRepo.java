package tk.exdeath.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.exdeath.model.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User readByUsername(String username);
}
