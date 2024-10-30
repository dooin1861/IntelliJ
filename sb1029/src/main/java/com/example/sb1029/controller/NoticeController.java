package com.example.sb1029.controller;

import com.example.sb1029.entity.Notice;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notice/")
@Log4j2
public class NoticeController {

    @GetMapping("/noticeList")
    public void showNoticeList(Model model) {
        List<Notice> notices = noticeService.getAllNotices(); // 예시로 서비스에서 리스트를 가져온다고 가정
        model.addAttribute("list", notices);
        return "notice/noticeList";
    }

}