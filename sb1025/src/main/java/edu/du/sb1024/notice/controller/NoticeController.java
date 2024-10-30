package edu.du.sb1024.notice.controller;

import edu.du.sb1024.notice.entity.Notice;
import edu.du.sb1024.notice.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
@Slf4j
public class NoticeController {

    @Autowired
    NoticeRepository noticeRepository;

    @GetMapping("/notice/noticeList")
    public void openNoticeList() {

    }

    @PostConstruct
    public void init() {
        Notice notice = Notice.builder()
                .noticeIdx(1001)
                .content("공지사항1")
                .title("공지사항1")
                .build();
        noticeRepository.save(notice);
    }
}
