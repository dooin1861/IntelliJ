package com.example.demo.forum.service;

import com.example.demo.forum.entity.Board;
import com.example.demo.forum.entity.Comment;
import com.example.demo.forum.repository.BoardRepository;
import com.example.demo.forum.repository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    public Comment addComment(Integer boardIdx, String content, String creatorId) {
        // 게시글 조회
        Optional<Board> boardOptional = boardRepository.findById(boardIdx);
        if (!boardOptional.isPresent()) {
            throw new RuntimeException("Board not found");
        }

        Board board = boardOptional.get();

        // 댓글 객체 생성
        Comment comment = Comment.builder()
                .board(board)
                .content(content)
                .creatorId(creatorId)
                .createdDatetime(LocalDateTime.now())
                .deletedYn("N")
                .build();

        // 댓글 저장
        return commentRepository.save(comment);
    }


    // 특정 게시글의 댓글 목록 조회
    public List<Comment> getCommentsByBoardIdx(Integer boardIdx) {
        return commentRepository.findByBoardBoardIdx(boardIdx);
    }

    @Transactional
    public void deleteComment(Integer commentIdx) {
        commentRepository.deleteById(commentIdx);  // commentIdx로 삭제
    }

}
