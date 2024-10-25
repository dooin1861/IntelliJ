package com.example.sb1024.service;

import com.example.sb1024.entity.Board;

import java.util.List;


public interface BoardService {
    // 게시글 목록 조회

    List<Board> selectBoardList();

    Board selectBoardDetail(Integer boardIdx); // 게시글 상세 조회
    void insertBoard(Board board); // 게시글 삽입
    void updateBoard(Board board); // 게시글 수정
    void deleteBoard(Integer boardIdx); // 게시글 삭제
}
