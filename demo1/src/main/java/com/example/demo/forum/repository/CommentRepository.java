package com.example.demo.forum.repository;

import com.example.demo.forum.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // 게시판 ID를 기준으로 댓글 목록 조회
    List<Comment> findByBoardBoardIdx(Integer boardIdx);

    void deleteById(Integer commentIdx);

    // 댓글 삭제 여부를 기준으로 댓글 조회

}