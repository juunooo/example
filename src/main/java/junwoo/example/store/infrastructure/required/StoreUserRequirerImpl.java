package junwoo.example.store.infrastructure.required;

import junwoo.example.user.application.provided.UserStoreProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StoreUserRequirerImpl implements StoreUserRequirer {
    private final UserStoreProvider userStoreProvider;

}
