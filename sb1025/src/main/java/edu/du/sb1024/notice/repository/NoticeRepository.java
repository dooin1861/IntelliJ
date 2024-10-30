package edu.du.sb1024.notice.repository;

import edu.du.sb1024.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    // 추가적인 쿼리 메서드를 정의할 수 있습니다.
}