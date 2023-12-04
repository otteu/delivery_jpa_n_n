package dev.example.db.repository.storemenu;

import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.storemenu.StoreMenuEntity;
import dev.example.db.domain.storemenu.enums.StoreMenuStatus;

import java.util.List;
import java.util.Optional;

public interface StoreMenuRepositoryCustom {

    Optional<StoreMenuEntity> findFirstByIdAndStatusOrderByIdDescs(StoreEntity storeEntity, StoreMenuStatus status);

    // 특정 가게의 메뉴 가져오기
    // select * from store_menu where store_id = ? and status = ? order by sequence desc;
    List<StoreMenuEntity> findAllByStoreIdAndStatusOrderBySequenceDescs(StoreEntity storeId, StoreMenuStatus status);


}
