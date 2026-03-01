package junwoo.example.store.domain;

import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface StoreRepository extends Repository<Store, UUID> {
}
