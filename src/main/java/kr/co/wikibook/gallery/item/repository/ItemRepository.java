package kr.co.wikibook.gallery.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.wikibook.gallery.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    // 여러 아이디로 상품을 조회
    List<Item> findAllByIdIn(List<Integer> ids);
}