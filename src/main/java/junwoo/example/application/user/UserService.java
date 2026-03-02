package junwoo.example.application.user;

import junwoo.example.domain.user.User;
import junwoo.example.domain.user.UserRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface UserService {
    User createUser(UserRequest userRequest);

    Slice<User> getUsers(Pageable pageable);

    User getUser(UUID userId);
}
