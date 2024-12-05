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
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;


@Log4j2
@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations simpMessagingTemplate;
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    private final Set<String> connectedUsers = new CopyOnWriteArraySet<>(); // 추가

    // 연결 시 이벤트 리스너 추가
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null) {
            log.info("사용자 연결됨: {}", username);

            try {
                MemberEntity member = memberRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + username));

                connectedUsers.add(username);
                sendParticipantList();

                log.info("현재 접속자 수: {}", connectedUsers.size());
            } catch (Exception e) {
                log.error("사용자 연결 처리 중 오류 발생", e);
            }
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null) {
            log.info("사용자 연결 끊김: {}", username);
            connectedUsers.remove(username); // 사용자 제거
            sendParticipantList(); // 참가자 목록 업데이트

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

    // 참가자 목록 전송 메서드 추가
    private void sendParticipantList() {
        try {
            List<String> nicknames = connectedUsers.stream()
                    .map(username -> memberRepository.findByUsername(username)
                            .map(MemberEntity::getNickname)
                            .orElse(username))
                    .collect(Collectors.toList());

            Map<String, Object> participantInfo = new HashMap<>();
            participantInfo.put("type", "PARTICIPANTS");
            participantInfo.put("count", connectedUsers.size());
            participantInfo.put("users", nicknames);

            simpMessagingTemplate.convertAndSend("/topic/participants", participantInfo);
        } catch (Exception e) {
            log.error("참가자 목록 전송 중 오류 발생", e);
        }
    }

    public void addConnectedUser(String username) {
        if (username != null) {
            connectedUsers.add(username);
            sendParticipantList();
            log.info("사용자 추가됨: {}, 현재 접속자 수: {}", username, connectedUsers.size());
        }
    }
}