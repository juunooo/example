package junwoo.example.store.application;

import junwoo.example.store.domain.StoreRepository;
import junwoo.example.store.infrastructure.required.StoreUserRequirer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final StoreUserRequirer storeUserRequirer;
    
}
