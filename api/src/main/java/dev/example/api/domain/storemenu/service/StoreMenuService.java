package dev.example.api.domain.storemenu.service;

import dev.example.api.common.error.ErrorCode;
import dev.example.api.common.exception.ApiException;
import dev.example.db.domain.store.StoreEntity;
import dev.example.db.domain.storemenu.StoreMenuEntity;
import dev.example.db.domain.storemenu.enums.StoreMenuStatus;
import dev.example.db.repository.storemenu.StoreMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;


    public StoreMenuEntity findOne(Long id) {
        StoreMenuEntity storeMenuEntity = storeMenuRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        return storeMenuEntity;
    }

    public StoreMenuEntity getStoreMenuWithThrow(StoreEntity storeEntity){


        var entity = storeMenuRepository.findFirstByIdAndStatusOrderByIdDescs(storeEntity, StoreMenuStatus.REGISTERED);
        return entity.orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreMenuEntity> getStoreMenuByStoreId(StoreEntity store){
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDescs(store, StoreMenuStatus.REGISTERED);
    }


    public StoreMenuEntity register(
            StoreMenuEntity storeMenuEntity
    ){
        return Optional.ofNullable(storeMenuEntity)
                .map(it ->{
                    it.setStatus(StoreMenuStatus.REGISTERED);
                    return storeMenuRepository.save(it);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));

    }

}
