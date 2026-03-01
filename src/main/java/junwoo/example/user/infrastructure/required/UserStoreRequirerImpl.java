package junwoo.example.user.infrastructure.required;

import junwoo.example.store.application.provided.StoreUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserStoreRequirerImpl implements UserStoreRequirer {
    private final StoreUserProvider storeUserProvider;

}
