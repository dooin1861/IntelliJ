package com.example.sb1024.service;

import com.example.sb1024.entity.Board;
import com.example.sb1024.repository.BoardRepository; // BoardRepository는 나중에 작성할 예정
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository; // Repository 주입

    @Override
    public List<Board> selectBoardList() {
        return boardRepository.findAll(); // 모든 게시글 조회
    }

    @Override
    public Board selectBoardDetail(Integer boardIdx) {
        return boardRepository.findById(boardIdx).orElse(null); // 게시글 상세 조회
    }

    @Override
    public void insertBoard(Board board) {
        boardRepository.save(board); // 게시글 삽입
    }

    @Override
    public void updateBoard(Board board) {
        boardRepository.save(board); // 게시글 수정
    }

    @Override
    public void deleteBoard(Integer boardIdx) {
        boardRepository.deleteById(boardIdx); // 게시글 삭제
    }
}
