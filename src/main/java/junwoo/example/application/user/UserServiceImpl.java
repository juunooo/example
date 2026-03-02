package junwoo.example.application.user;

import jakarta.validation.Valid;
import junwoo.example.domain.user.User;
import junwoo.example.domain.user.UserRepository;
import junwoo.example.domain.user.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Primary
@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User createUser(@Valid UserRequest userRequest) {
        User user = User.createUser(userRequest);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Slice<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public User getUser(UUID userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException(String.format("id : %s 유저는 없습니다.", userId))
        );
    }
}
