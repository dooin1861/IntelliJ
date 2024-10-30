package edu.du.sb1024.notice.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Entity
@Table(name= "notice")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeIdx;
    private String title;
    private String content;

    @ColumnDefault("0") //default 0
    private Integer hitCnt;

    private String creatorId;

    private String createdDatetime;

    private String updaterId;

    private String updatedDatetime;

    @Column(columnDefinition = "varchar(2) default 'N'")
    private String deletedYn;
}
