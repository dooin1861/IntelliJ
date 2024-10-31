package com.example.sb1031.event;

import com.example.sb1031.order.Order;
import com.example.sb1031.order.OrderService;
import com.example.sb1031.shipment.Shipment;
import com.example.sb1031.shipment.ShipmentRepository;
import com.example.sb1031.shipment.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration // 이 클래스가 스프링의 설정 클래스를 의미함을 나타냄
@Slf4j // 로깅 기능을 사용할 수 있게 해줌
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성해주는 롬복 어노테이션
public class CustomEventListener {

    private final ShipmentRepository shipmentRepository; // ShipmentRepository 인스턴스를 주입받음

    @EventListener // 이 메서드가 이벤트를 수신하는 리스너임을 나타냄
    public void handleContextStart(CustomEvent customEvent) {
        // 이벤트를 처리하기 위한 메서드. CustomEvent를 인자로 받음.
        log.info("Handling context started event. message : {}", customEvent.getMessage()); // 이벤트 처리 시작 로그

        Order order = customEvent.getMessage(); // 이벤트에서 주문 정보를 가져옴
        // Shipment 객체를 생성하는 빌더 패턴을 사용
        Shipment shipment = Shipment.builder()
                .orderId(order.getId()) // 주문 ID 설정
                .price(order.getPrice()) // 가격 설정
                .productName(order.getProductName()) // 제품 이름 설정
                .quantity(order.getQuantity()) // 수량 설정
                .build(); // 객체 생성 완료

        shipmentRepository.save(shipment); // 생성한 Shipment 객체를 데이터베이스에 저장
    }
}
