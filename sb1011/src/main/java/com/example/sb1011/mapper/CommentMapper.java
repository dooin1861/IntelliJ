package com.example.sb1011.mapper;

import com.example.sb1011.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDto> selectCommentsByBoardId(int boardIdx) throws Exception;

    void insertComment(CommentDto comment) throws Exception;

    void deleteComment(int commentId);

    void updateComment(CommentDto comment);
    // 필요에 따라 추가적인 메서드 정의
}