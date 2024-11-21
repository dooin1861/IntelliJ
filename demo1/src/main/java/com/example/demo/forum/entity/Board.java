package com.example.demo.forum.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name= "t_board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardIdx;

	@NotBlank(message = "제목을 입력해주세요.")
	private String title;

	@NotBlank(message = "내용을 입력해주세요.")
	private String contents;

	@ColumnDefault("0") //default 0
	private Integer hitCnt;
	
	private String creatorId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private String createdDatetime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private String updatedDatetime;

	@Column(columnDefinition = "varchar(2) default 'N'")
	private String deletedYn;

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // board 엔티티랑 관계 설정
	private List<Comment> comments;  // 댓글 목록

}
