package com.example.sb1031.event;

import com.example.sb1031.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// EventController 클래스는 이벤트를 처리하는 REST 컨트롤러입니다.
@RestController
@RequiredArgsConstructor // 의존성 주입을 위한 생성자 자동 생성
@Slf4j // 로깅을 위한 Lombok 어노테이션
public class EventController {

    // CustomEventPublisher 인스턴스를 주입받아 이벤트 발행을 처리
    private final CustomEventPublisher customEventPublisher;

    // 클라이언트의 GET 요청을 처리하는 메서드
    @GetMapping("/event/{msg}")
    public void event(@PathVariable Order msg) {
        // 수신한 메시지를 로그에 기록
        log.info(msg.toString());

        // 주문 메시지를 퍼블리셔에 전달하여 이벤트 발행
        customEventPublisher.doStuffAndPublishAnEvent(msg);
    }
}
