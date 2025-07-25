package kr.co.wikibook.gallery.cart.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.wikibook.gallery.cart.dto.CartRead;
import lombok.Getter;

@Getter
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer memberId;

    @Column(nullable = false)
    private Integer itemId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public Cart() {
    }

    public Cart(Integer memberId, Integer itemId) {
        this.memberId = memberId;
        this.itemId = itemId;
    }

    // 장바구니 조회 DTO로 변환
    public CartRead toRead() {
        return CartRead.builder()
                .id(id)
                .itemId(itemId)
                .build();
    }
}