package com.example.sb1031.event;

import com.example.sb1031.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스는 Spring의 설정 클래스를 나타냅니다.
public class CustomEventPublisher {

    @Autowired // Spring이 이 필드를 자동으로 주입하도록 합니다.
    private ApplicationEventPublisher applicationEventPublisher; // 이벤트를 발행하기 위한 퍼블리셔입니다.

    // 주어진 Order 객체를 처리하고 이벤트를 발행하는 메서드
    public void doStuffAndPublishAnEvent(final Order message) {
        System.out.println("Publishing custom event."); // 이벤트 발행 시작을 알리는 메시지 출력

        // CustomEvent 객체를 생성합니다. this는 현재 인스턴스를 의미하고, message는 전달된 주문 정보입니다.
        CustomEvent customSpringEvent = new CustomEvent(this, message);

        // 생성한 CustomEvent를 ApplicationEventPublisher를 통해 발행합니다.
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
