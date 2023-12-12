package com.example.termproject.repository;

import com.example.termproject.entity.Board;
import com.example.termproject.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBno(@Param("bno")Long bno);
    // 특정 게시물에 대한 댓글 목록 조회하는 기능
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}