package com.example.sb1127.tab.dto;

import lombok.Data;

@Data

public class NoteRequest {
    private int string;      // 기타 줄 번호
    private int fret;        // 프렛 번호
    private int position;    // 악보상 위치
    private String duration; // 음표 길이
}