package com.example.sb1011.dto;

import lombok.Data;

@Data
public class CommentDto {
    private int commentId;       // 댓글 ID
    private int boardIdx;        // 게시글 ID
    private String commentText;   // 댓글 내용
    private String createdAt;     // 작성 시간
}
