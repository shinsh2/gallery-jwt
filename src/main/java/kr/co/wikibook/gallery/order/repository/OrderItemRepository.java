package kr.co.wikibook.gallery.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.wikibook.gallery.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    // 주문 상품 목록 조회
    List<OrderItem> findAllByOrderId(Integer orderId);
}