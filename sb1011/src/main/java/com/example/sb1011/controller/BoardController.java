package com.example.sb1011.controller;

import com.example.sb1011.dto.BoardDto;
import com.example.sb1011.dto.CommentDto;
import com.example.sb1011.service.BoardService;
import com.example.sb1011.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@Autowired
	private CommentService commentService;

	@GetMapping("/")
	public String index() {
		return "redirect:/board/openBoardList.do"; // 페이지를 열면 BoardList가 나오게끔
	}
	
	@GetMapping("/board/openBoardList.do")  // 게시글 리스트 열기
	public String openBoardList(Model model) throws Exception {
		
		List<BoardDto> list = boardService.selectBoardList();
		model.addAttribute("list", list);
		
		return "/board/boardList";
	}
	
	@RequestMapping("/board/openBoardWrite.do") // 글 작성창 열기
	public String openBoardWrite() throws Exception{
		return "/board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do") // 게시판에 글 작성
	public String insertBoard(BoardDto board) throws Exception{
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@GetMapping("/board/openBoardDetail.do") // 게시판 자세히
	public String openBoardDetail(@RequestParam int boardIdx, Model model) throws Exception{

		BoardDto board = boardService.selectBoardDetail(boardIdx);
		List<CommentDto> comments = commentService.getCommentsByBoardId(boardIdx);
		model.addAttribute("board", board);
		model.addAttribute("comments", comments);
		return "/board/boardDetail";
	}

	@RequestMapping("/board/updateBoard.do") // 게시글 업데이트
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/deleteBoard.do") // 게시글 삭제
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}

	@PostMapping("/board/insertComment.do") // 댓글 추가
	public String insertComment(@RequestParam int boardIdx, @RequestParam String comment) throws Exception {
		CommentDto commentDto = new CommentDto();
		commentDto.setBoardIdx(boardIdx);
		commentDto.setCommentText(comment);
		commentService.addComment(commentDto);
		return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx; // 댓글 추가 후 상세보기로 리다이렉트
	}	// + boardIdx가 없는 경우 상단 URL주소에 글 번호가 숫자로 보이지 않음.

	@PostMapping("/board/updateComment.do")  // 댓글 수정
	public String updateComment(@RequestParam int commentId, @RequestParam int boardIdx, @RequestParam String commentText) throws Exception {
		CommentDto commentDto = new CommentDto();
		commentDto.setCommentId(commentId);
		commentDto.setBoardIdx(boardIdx);
		commentDto.setCommentText(commentText); // 수정된 댓글 내용
		commentService.updateComment(commentDto); // 댓글 수정 서비스 호출
		return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx; // 수정 후 게시글 상세 페이지로 리다이렉트
	}
	
	@PostMapping("/board/deleteComment.do") // 댓글 삭제
	public String deleteComment(@RequestParam int commentId, @RequestParam int boardIdx) throws Exception {
		commentService.deleteComment(commentId);
		return "redirect:/board/openBoardDetail.do?boardIdx=" + boardIdx;
	}

}
