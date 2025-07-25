package kr.co.wikibook.gallery.block.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.wikibook.gallery.block.entity.Block;

public interface BlockRepository extends JpaRepository<Block, Integer> {

    // 토큰 차단 데이터 조회
    Optional<Block> findByToken(String token);
}