package junwoo.example.domain.store;

import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface StoreRepository extends Repository<Store, UUID> {
}
