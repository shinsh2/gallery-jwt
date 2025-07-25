package kr.co.wikibook.gallery.order.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.wikibook.gallery.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // 주문 목록 조회
    Page<Order> findAllByMemberIdOrderByIdDesc(Integer memberId, Pageable pageable);

    // 주문 정보 조회(특정 아이디 및 특정 회원)
    Optional<Order> findByIdAndMemberId(Integer id, Integer memberId);
}