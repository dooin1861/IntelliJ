package com.example.sb1011.service.impl;

import com.example.sb1011.dto.CommentDto;
import com.example.sb1011.mapper.CommentMapper;
import com.example.sb1011.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDto> getCommentsByBoardId(int boardIdx) throws Exception {
        return commentMapper.selectCommentsByBoardId(boardIdx);
    }

    @Override
    public void addComment(CommentDto comment) throws Exception {
        comment.setCreatedAt(java.time.LocalDateTime.now().toString());
        commentMapper.insertComment(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentMapper.deleteComment(commentId); // mapper 호출
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        commentMapper.updateComment(commentDto);
    } // delete와는 다르게 댓글의 모든 정보가 필요해서 Id가 아닌 Dto.
}
