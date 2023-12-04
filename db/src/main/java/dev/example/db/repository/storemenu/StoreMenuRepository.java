package dev.example.db.repository.storemenu;

import dev.example.db.domain.storemenu.StoreMenuEntity;
import dev.example.db.domain.storemenu.enums.StoreMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface StoreMenuRepository extends JpaRepository<StoreMenuEntity, Long>, StoreMenuRepositoryCustom {




}
