package kr.co.wikibook.gallery.block.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "blocks")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 250, nullable = false)
    private String token;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public Block() {
    }

    public Block(String token) {
        this.token = token;
    }
}