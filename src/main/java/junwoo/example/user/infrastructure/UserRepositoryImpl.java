package junwoo.example.user.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import junwoo.example.user.domain.User;
import junwoo.example.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public Slice<User> findAll(Pageable pageable) {
        return userJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return userJpaRepository.findById(userId);
    }
}
