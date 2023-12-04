package dev.example.db.repository.order.model;

import dev.example.db.domain.order.OrderEntity;
import dev.example.db.domain.ordermenu.OrderMenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//
//@Repository
//@RequiredArgsConstructor
//public class OrderQueryRepository {

//    private final EntityManager em;
//
//
//    public List<OrderEntity> findOrderQueryOrld() {
//        List<OrderEntity> findOrders = findOrders();
//
//        List<Long> orderIds = findOrders.stream()
//                .map(o -> o.getId())
//                .collect(Collectors.toList());
//
//        // 메뉴 IN 절
//        List<OrderMenuEntity> orderMenus = findOrderMenus(orderIds);
//
//        // OrderId를 Key로 묶음
//        Map<Long, List<OrderMenuEntity>> orderMenuMap = orderMenus.stream()
//                .collect(Collectors.groupingBy(o -> o.getOrder().getId()));
//
//        // Order MenuList set 해주는 과정
//        findOrders.forEach(o ->
//                o.setOrderMenuList(orderMenuMap.get(o.getId())));
//
//        return findOrders;
//    }
//
//
//    // 메뉴 리스트 조회
//    public List<OrderMenuEntity> findOrderMenus(List<Long> orderIds) {
//
//        List<OrderMenuEntity> orderMenu = em.createQuery(
//                        "select ome " +
//                                "from OrderMenuEntity ome " +
//                                "join ome.storeMenu sm " +
//                                "where ome.storeMenu.id in :orderIds", OrderMenuEntity.class)
//                .setParameter("orderIds", orderIds)
//                .getResultList();
//
//        return orderMenu;
//    }
//
//    // 주문 조회
//    public List<OrderEntity> findOrders() {
//
//        List<OrderEntity> orders = em.createQuery(
//                        "select oe " +
//                                "from OrderEntity oe " +
//                                "join oe.user u ", OrderEntity.class)
//                .getResultList();
//
//        return orders;
//    }



//}
