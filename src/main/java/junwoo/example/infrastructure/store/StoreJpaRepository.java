package junwoo.example.infrastructure.store;

import junwoo.example.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreJpaRepository extends JpaRepository<Store, UUID> {
}
