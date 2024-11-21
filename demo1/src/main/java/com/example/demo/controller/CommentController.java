package com.example.demo.controller;

import com.example.demo.forum.dto.BoardDto;
import com.example.demo.forum.entity.Comment;
import com.example.demo.forum.service.BoardService;
import com.example.demo.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BoardService boardService;

    @GetMapping("/boardDetail/{boardIdx}")
    public String viewBoardDetail(@PathVariable Integer boardIdx, Model model) throws Exception {
        BoardDto board = boardService.selectBoardDetail(boardIdx); // 게시글 상세 정보
        List<Comment> comments = commentService.getCommentsByBoardIdx(boardIdx); // 댓글 조회

        model.addAttribute("board", board);
        model.addAttribute("comments", comments);

        return "board/boardDetail";  // boardDetail.html로 반환
    }

    @PostMapping("/{boardIdx}/comments")
    public String postComment(@PathVariable Integer boardIdx,
                              @RequestParam String content,
                              @RequestParam String creatorId) {
        // 댓글 추가
        commentService.addComment(boardIdx, content, creatorId);

        // 댓글 작성 후 게시글 상세 페이지로 리다이렉트
        return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx;
    }

    // 댓글 삭제
    @PostMapping("/comments/{commentIdx}/delete")
    public String deleteComment(@PathVariable Integer commentIdx,
                                @RequestParam Integer boardIdx) {
        commentService.deleteComment(commentIdx);

        return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx;  // 댓글 삭제 후 게시글 상세 페이지로 리다이렉트
    }

}
