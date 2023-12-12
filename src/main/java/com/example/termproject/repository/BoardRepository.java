package com.example.termproject.repository;

import com.example.termproject.entity.Board;
import com.example.termproject.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {
    // Lazy 방식에서는 Join이 필요할 때 JPQL을 사용해서 Join에 필요한 sql문을 작성해야함.
    @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
    // 바로 참조 못해서 :bno 라고 해준다 밑에 어노테이션을 이용해서?
    Object getBoardWithWriter(@Param("bno") Long bno);

    // 목록 화면에 사용될 데이터행의 결과 집합을 얻을 수 이슨 JPQL
    @Query("select b, r from Board b left join Reply r ON r.board = b where b.bno=:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);
    // 목록 화면에 사용되 데이터행의 결과 + 댓글의 개수 : JPQL
    @Query(value = "select b, w, count(r) from Board b " +
            " left join b.writer w " +
            " left join Reply r ON r.board = b " +
            "group by b" ,
            countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("select b, w, count(r) " +
            "from Board b left join b.writer w " +
            "left outer join Reply r " +
            "on r.board = b " +
            "where b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);
}
