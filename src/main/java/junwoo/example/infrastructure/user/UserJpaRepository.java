package junwoo.example.infrastructure.user;

import junwoo.example.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserJpaRepository extends JpaRepository<User, UUID> {
}
