package junwoo.example.store.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import junwoo.example.store.domain.StoreRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {
    private final StoreJpaRepository storeJpaRepository;
    private final JPAQueryFactory queryFactory;
}
