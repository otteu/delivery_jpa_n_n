package dev.example.api.domain.store.service;


import dev.example.api.common.error.ErrorCode;
import dev.example.api.common.exception.ApiException;
import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.store.enums.StoreCategory;
import dev.example.db.domain.store.enums.StoreStatus;
import dev.example.db.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;


    public StoreEntity getStoreOneWithThrow(Long id) {
        Optional<StoreEntity> entity = storeRepository.findByIdAndStatus(id, StoreStatus.REGISTERED);
        return entity.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 유효환 스토어
    public StoreEntity getStoreWithThrow(Long id) {
        Optional<StoreEntity> entity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED);
        return entity.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 스토어 등록
    // 등록시 Optinal 로 NULL 채크
    public StoreEntity register(StoreEntity storeEntity) {
        return Optional.ofNullable(storeEntity)
                .map(it -> {
                    it.setStar(0);
                    it.setStatus(StoreStatus.REGISTERED);

                    return storeRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    // 카테고리 스토어 검색
    public List<StoreEntity> searchByCategory(StoreCategory storeCategory) {
        List<StoreEntity> list = storeRepository.findAllByStatusAndCategoryOrderByStarDesc(
                StoreStatus.REGISTERED,
                storeCategory
        );
        return list;
    }

    // 전체 스토어 조회
    public List<StoreEntity> registerStore() {
        List<StoreEntity> list = storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
        return list;
    }


}
