package kr.co.wikibook.gallery.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.wikibook.gallery.order.entity.OrderItem;
import kr.co.wikibook.gallery.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseOrderItemService implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    // 주문 목록 조회
    @Override
    public List<OrderItem> findAll(Integer orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }

    // 주문 상품 데이터 저장
    @Override
    public void saveAll(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }
}