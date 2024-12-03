package com.example.sb1128.chat.service;

import com.example.sb1128.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatCleanupService {

    private final ChatRepository chatRepository;

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    @Transactional
    public void cleanupOldMessages() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        try {
            chatRepository.deleteOldMessages(oneWeekAgo);
            log.info("1주일 이전의 채팅 메시지가 삭제되었습니다. 기준 시간: {}", oneWeekAgo);
        } catch (Exception e) {
            log.error("채팅 메시지 정리 중 오류 발생", e);
        }
    }
}
