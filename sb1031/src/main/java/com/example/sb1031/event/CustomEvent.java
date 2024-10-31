package com.example.sb1031.event;
import com.example.sb1031.order.Order;
import org.springframework.context.ApplicationEvent;

// CustomEvent 클래스는 주문과 관련된 이벤트를 나타냅니다.
public class CustomEvent extends ApplicationEvent {

    // 이벤트와 관련된 주문 정보를 저장하는 필드
    private Order message;

    // 생성자: CustomEvent 객체를 생성할 때 이벤트의 출처와 주문 메시지를 초기화합니다.
    public CustomEvent(Object source, Order message) {
        super(source); // ApplicationEvent의 생성자를 호출하여 출처를 설정
        this.message = message; // 주문 메시지를 초기화
    }

    // 주문 메시지를 반환하는 getter 메서드
    public Order getMessage() {
        return message; // 주문 정보를 반환
    }
}
