package junwoo.example.store.application.provided;

import junwoo.example.store.domain.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StoreUserProviderImpl implements StoreUserProvider {
    private final StoreRepository storeRepository;

}
