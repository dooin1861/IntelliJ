package edu.du.sb1024.notice.repository;

import edu.du.sb1024.notice.entity.Notice;
import edu.du.sb1024.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public List<Notice> selectNoticeList() {
        // 데이터베이스에서 공지사항 목록을 가져오는 로직
        return noticeRepository.findAll(); // Repository의 메서드 사용
    }
}