package com.example.demo.forum.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_comment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentIdx;  // 댓글 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_idx")
    private Board board;  // 해당 댓글이 달린 게시판

    private String content;  // 댓글 내용

    private String creatorId;  // 댓글 작성자

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDatetime;  // 댓글 작성일시

    @Column(columnDefinition = "varchar(2) default 'N'")
    private String deletedYn;  // 삭제 여부 (기본값 'N')
}
