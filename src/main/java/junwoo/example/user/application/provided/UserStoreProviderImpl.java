package junwoo.example.user.application.provided;

import junwoo.example.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserStoreProviderImpl implements UserStoreProvider {
    private final UserRepository userRepository;

}
