package com.example.demo.controller;

import com.example.demo.forum.dto.BoardDto;
import com.example.demo.forum.dto.BoardFileDto;
import com.example.demo.forum.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/openBoardList.do")
	public String openBoardList(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) throws Exception { // 0번 페이지, 10개
		log.info("====> openBoardList {}", "테스트");

		List<BoardDto> list = boardService.selectBoardList();
		final int start = (int) pageable.getOffset();
		final int end = Math.min(start + pageable.getPageSize(), list.size());
		log.info("start : {}, end : {}", start, end);
		final Page<BoardDto> page = new PageImpl<>(list.subList(start, end), pageable, list.size());

		log.info("Board List: {}", list);
		log.info("총 페이지 수: {}", page.getTotalPages());
		log.info("전체 개수 : {}", page.getTotalElements());
		log.info("현재 페이지 번호: {}", page.getNumber());
		log.info("페이지당 데이터 개수: {}", page.getSize());
		log.info("다음 페이지 존재 여부: {}", page.hasNext());
		log.info("이전 페이지 존재 여부: {}", page.hasPrevious());
		log.info("시작페이지(0) 입니까: {}", page.isFirst());
		model.addAttribute("list", page);
		
		return "/board/boardList";
	}

	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite(Model model) throws Exception {
		model.addAttribute("board", new BoardDto());  // board 객체를 추가
		return "board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		boardService.insertBoard(board, multipartHttpServletRequest);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		System.out.println(board);
		return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception{
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/downloadBoardFile.do")
	public void downloadBoardFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response) throws Exception{
		String currentPath = Paths.get("").toAbsolutePath().toString();
		System.out.println("---------------------"+currentPath);
		BoardFileDto boardFile = boardService.selectBoardFileInformation(idx, boardIdx);
		if(ObjectUtils.isEmpty(boardFile) == false) {
			String fileName = boardFile.getOriginalFileName();
			
			byte[] files = FileUtils.readFileToByteArray(new File("./src/main/resources/static"+boardFile.getStoredFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}