package kr.co.wikibook.gallery.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import kr.co.wikibook.gallery.item.dto.ItemRead;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderRead {

    private Integer id;
    private String name;
    private String address;
    private String payment;
    private Long amount;
    private LocalDateTime created;
    private List<ItemRead> items;
}