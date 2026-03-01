package junwoo.example.store.infrastructure;

import junwoo.example.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreJpaRepository extends JpaRepository<Store, UUID> {
}
