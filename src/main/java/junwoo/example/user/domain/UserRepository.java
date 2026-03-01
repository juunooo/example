package junwoo.example.user.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {
    User save(User user);
    Slice<User> findAll(Pageable pageable);
    Optional<User> findById(UUID userId);
}
