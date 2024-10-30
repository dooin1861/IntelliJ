package edu.du.sb1024.notice.service;

import edu.du.sb1024.notice.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> selectNoticeList(); // 공지사항 목록을 반환하는 메서드
}
