package kr.co.wikibook.gallery.item.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.wikibook.gallery.item.dto.ItemRead;
import kr.co.wikibook.gallery.item.service.ItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/api/items")
    public ResponseEntity<?> readAll() {
        List<ItemRead> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}