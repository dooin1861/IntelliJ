package com.example.demo.forum.dto;

import lombok.Data;

import java.util.List;

@Data
public class BoardDto {
	
	private int boardIdx;
	
	private String title;
	
	private String contents;
	
	private int hitCnt;
	
	private String creatorId;
	
	private String createdDatetime;
	
	private String updatedDatetime;

	private List<BoardFileDto> fileList;
}
