package com.example.sb1128.chat.eventListener;

import com.example.sb1128.chat.dto.ChatDto;
import com.example.sb1128.chat.entity.ChatEntity;
import com.example.sb1128.chat.repository.ChatRepository;
import com.example.sb1128.member.entity.MemberEntity;
import com.example.sb1128.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.LocalDateTime;

@Log4j2
@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations simpMessagingTemplate;
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null) {
            log.info("사용자 연결 끊김: {}", username);

            try {
                MemberEntity member = memberRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + username));

                // 퇴장 메시지 저장 및 전송
                ChatEntity chatEntity = new ChatEntity();
                chatEntity.setContent(member.getNickname() + "님의 연결이 끊어졌습니다.");
                chatEntity.setType(ChatEntity.MessageType.LEAVE);
                chatEntity.setSender(member);
                chatRepository.save(chatEntity);

                ChatDto chatDto = ChatDto.builder()
                        .type(ChatDto.MessageType.LEAVE)
                        .sender(member.getNickname())
                        .originalSender(username)
                        .content(member.getNickname() + "님의 연결이 끊어졌습니다.")
                        .sendTime(LocalDateTime.now())
                        .build();

                simpMessagingTemplate.convertAndSend("/topic/public", chatDto);
            } catch (Exception e) {
                log.error("연결 끊김 처리 중 오류 발생", e);
            }
        }
    }
}