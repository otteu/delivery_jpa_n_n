package dev.example.db.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;

import dev.example.db.domain.user.QUserEntity;
import dev.example.db.domain.user.UserEntity;
import dev.example.db.domain.user.enums.UserStatus;

import javax.persistence.EntityManager;
import java.util.Optional;

import static dev.example.db.domain.user.QUserEntity.userEntity;


public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

//    @Override
//    public Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, UserStatus status) {
//        return queryFactory
//                .selectFrom(userEntity)
//                .where(userEntity.)
//
//    }


}
