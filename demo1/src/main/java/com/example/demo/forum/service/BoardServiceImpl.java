package com.example.demo.forum.service;

import com.example.demo.forum.common.FileUtils;
import com.example.demo.forum.dto.BoardDto;
import com.example.demo.forum.dto.BoardFileDto;
import com.example.demo.forum.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	CommentService commentService;

	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardMapper.insertBoard(board);
		List<BoardFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false){
			boardMapper.insertBoardFileList(list);
		}
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception{
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		System.out.println(board);

		List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
		board.setFileList(fileList);
		
		boardMapper.updateHitCount(boardIdx);
		
		return board;
	}
	
	@Override
	public void updateBoard(BoardDto board) throws Exception {
		boardMapper.updateBoard(board);
	}

	@Transactional
	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
//		commentService.deleteComment(boardIdx);
	}
	
	@Override
	public BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception {
		return boardMapper.selectBoardFileInformation(idx, boardIdx);
	}
}	

