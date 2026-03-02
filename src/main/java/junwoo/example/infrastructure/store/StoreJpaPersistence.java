package junwoo.example.infrastructure.store;

import com.querydsl.jpa.impl.JPAQueryFactory;
import junwoo.example.domain.store.StoreRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class StoreJpaPersistence implements StoreRepository {
    private final StoreJpaRepository storeJpaRepository;
    private final JPAQueryFactory queryFactory;
}
