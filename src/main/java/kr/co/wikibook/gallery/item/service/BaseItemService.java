package kr.co.wikibook.gallery.item.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.wikibook.gallery.item.dto.ItemRead;
import kr.co.wikibook.gallery.item.entity.Item;
import kr.co.wikibook.gallery.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseItemService implements ItemService {

    private final ItemRepository itemRepository;

    // 전체 상품 목록 조회
    @Override
    public List<ItemRead> findAll() {
        return itemRepository.findAll().stream().map(Item::toRead).toList();
    }

    // 상품 목록 조회(특정 아이디 리스트로 조회)
    @Override
    public List<ItemRead> findAll(List<Integer> ids) {
        return itemRepository.findAllByIdIn(ids).stream().map(Item::toRead).toList();
    }
}