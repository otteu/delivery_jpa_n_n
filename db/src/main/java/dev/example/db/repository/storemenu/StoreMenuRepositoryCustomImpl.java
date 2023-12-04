package dev.example.db.repository.storemenu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.example.db.domain.store.QStoreEntity;
import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.storemenu.QStoreMenuEntity;
import dev.example.db.domain.storemenu.StoreMenuEntity;
import dev.example.db.domain.storemenu.enums.StoreMenuStatus;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static dev.example.db.domain.store.QStoreEntity.storeEntity;
import static dev.example.db.domain.storemenu.QStoreMenuEntity.storeMenuEntity;

public class StoreMenuRepositoryCustomImpl implements StoreMenuRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public StoreMenuRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Optional<StoreMenuEntity> findFirstByIdAndStatusOrderByIdDescs(
            StoreEntity store,
            StoreMenuStatus status
    ) {

        StoreMenuEntity findStoreMenuEntity = queryFactory.selectFrom(storeMenuEntity)
                .leftJoin(storeMenuEntity.store, storeEntity)
                .where(
                        storeMenuEntity.status.eq(status),
                        storeMenuEntity.store.eq(store)
                )
                .fetchOne();

        return Optional.of(findStoreMenuEntity);

    }




    @Override
    public List<StoreMenuEntity> findAllByStoreIdAndStatusOrderBySequenceDescs(
            StoreEntity store,
            StoreMenuStatus status
    ) {
        List<StoreMenuEntity> fetch = queryFactory.selectFrom(storeMenuEntity)
                .leftJoin(storeMenuEntity.store, storeEntity)
                .where(
                        storeMenuEntity.status.eq(status),
                        storeMenuEntity.store.eq(store)
                )
                .fetch();

        return fetch;
    }
}
