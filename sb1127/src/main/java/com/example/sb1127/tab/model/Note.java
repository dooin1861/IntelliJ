package com.example.sb1127.tab.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int string;      // 기타 줄 번호 (1-6)
    private int fret;        // 프렛 번호
    private int position;    // 악보상 위치
    private String duration; // 음표 길이 (전체음표, 2분음표 등)

    @ManyToOne
    private Score score;
}