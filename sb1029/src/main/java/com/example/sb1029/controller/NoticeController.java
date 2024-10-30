package com.example.sb1029.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice/")
@Log4j2
public class NoticeController {

    @GetMapping("/noticeList")
    public String showNoticeList(Model model) {
        // 필요한 데이터 추가
        // model.addAttribute("notices", noticeService.getAllNotices());
        return "notice/noticeList"; // templates/notice/noticeList.html로 이동
    }

}