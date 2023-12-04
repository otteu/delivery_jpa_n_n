package dev.example.db.repository.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.ordermenu.OrderMenuEntity;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static dev.example.db.domain.order.QOrderEntity.orderEntity;
import static dev.example.db.domain.ordermenu.QOrderMenuEntity.orderMenuEntity;
import static dev.example.db.domain.storemenu.QStoreMenuEntity.storeMenuEntity;
import static dev.example.db.domain.user.QUserEntity.userEntity;


public class OrderRepositoryCustomImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final EntityManager ems;

    public OrderRepositoryCustomImpl(EntityManager em, EntityManager ems) {
        this.queryFactory = new JPAQueryFactory(em);
        this.ems = ems;
    }

    @Override
    public List<OrderEntity> findOrderQueryDtos() {
        List<OrderEntity> result = findAllWithUser();

        List<Long> orderIds = result.stream()
                .map(o -> o.getId())
                .collect(Collectors.toList());

        Map<Long, List<OrderMenuEntity>> orderMenuMap = findOrderMenus(orderIds);

        result.forEach(o -> o.setOrderMenuList(orderMenuMap.get(o.getId())));

        return result;
    }

    @Override
    public List<OrderEntity> findAllWithUser() {

        return queryFactory
                .selectFrom(orderEntity)
                .join(orderEntity.user)
                .join(orderEntity.user, userEntity) //.fetchJoin()
//                .where(orderEntity.user.eq(user))
                .fetch()
                ;
    }

    @Override
    public Map<Long, List<OrderMenuEntity>> findOrderMenus(List<Long> orderIds) {

        List<OrderMenuEntity> orderMenus = queryFactory
                .selectFrom(orderMenuEntity)
                .join(orderMenuEntity.storeMenu, storeMenuEntity)
                .where(orderMenuEntity.order.id.in(orderIds))
                .fetch();

        Map<Long, List<OrderMenuEntity>> orderMenuMap = orderMenus.stream()
                .collect(Collectors.groupingBy(o -> o.getOrder().getId()));


        return  orderMenuMap;
    }


    @Override
    public List<OrderEntity> findOrderQueryOrd() {
        List<OrderEntity> findOrders = findOrdersOrd();

        List<Long> orderIds = findOrders.stream()
                .map(o -> o.getId())
                .collect(Collectors.toList());

        // 메뉴 IN 절
        List<OrderMenuEntity> orderMenus = findOrderMenusOrd(orderIds);

        // OrderId를 Key로 묶음
        Map<Long, List<OrderMenuEntity>> orderMenuMap = orderMenus.stream()
                .collect(Collectors.groupingBy(o -> o.getOrder().getId()));

        // Order MenuList set 해주는 과정
        findOrders.forEach(o ->
                o.setOrderMenuList(orderMenuMap.get(o.getId())));

        return findOrders;
    }


    // 메뉴 리스트 조회
    private List<OrderMenuEntity> findOrderMenusOrd(List<Long> orderIds) {

        List<OrderMenuEntity> orderMenu = ems.createQuery(
                        "select ome " +
                                "from OrderMenuEntity ome " +
                                "join ome.storeMenu sm " +
                                "where ome.storeMenu.id in :orderIds", OrderMenuEntity.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        return orderMenu;
    }

    // 주문 조회
    private List<OrderEntity> findOrdersOrd() {

        List<OrderEntity> orders = ems.createQuery(
                        "select oe " +
                                "from OrderEntity oe " +
                                "join oe.user u ", OrderEntity.class)
                .getResultList();

        return orders;
    }




}
