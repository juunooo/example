package junwoo.example.user.infrastructure;

import junwoo.example.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserJpaRepository extends JpaRepository<User, UUID> {
}
