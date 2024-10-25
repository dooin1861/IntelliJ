package com.example.sb1011.service;

import com.example.sb1011.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getCommentsByBoardId(int boardIdx) throws Exception;
    void addComment(CommentDto comment) throws Exception;
    void deleteComment(int commentId);
    void updateComment(CommentDto commentDto) throws Exception ;
}