package junwoo.example.store.presentation;

import junwoo.example.store.application.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoreController {
    private final StoreService storeService;
}
