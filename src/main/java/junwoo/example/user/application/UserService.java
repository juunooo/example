package junwoo.example.user.application;

import junwoo.example.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface UserService {
    User createUser(UserRequest userRequest);

    Slice<User> getUsers(Pageable pageable);

    User getUser(UUID userId);
}
