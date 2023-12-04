package dev.example.db.repository.store;

import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.store.enums.StoreCategory;
import dev.example.db.domain.store.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {



    Optional<StoreEntity> findByIdAndStatus(Long id, StoreStatus status);

    // select * from store
    // where id = ? and status = ?
    // order by id desc
    // limit 1
    Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus status);


    // 유효한 스토어 리스트
    // select * from store
    // where status = ?
    // order by id desc
    List<StoreEntity> findAllByStatusOrderByIdDesc(StoreStatus status);

    // 유효한 특정 카테고리의 스토어 리스트
    // select * from store
    // where status = ?
    // and category = ?
    // order by star desc
    List<StoreEntity> findAllByStatusAndCategoryOrderByStarDesc(StoreStatus status, StoreCategory storeCategory);

}
