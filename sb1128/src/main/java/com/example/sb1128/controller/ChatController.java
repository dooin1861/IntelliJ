package com.example.sb1128.controller;

import com.example.sb1128.chat.dto.ChatDto;
import com.example.sb1128.chat.entity.ChatEntity;
import com.example.sb1128.chat.repository.ChatRepository;
import com.example.sb1128.member.entity.MemberEntity;
import com.example.sb1128.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Log4j2
public class ChatController {

    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;  // 사용자 정보 조회용
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/")
    public String chat() {
        return "chat";
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatDto sendMessage(@Payload ChatDto chatDto) {
        try {
            log.info("메시지 수신: {}", chatDto);  // 로깅 추가

            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setContent(chatDto.getContent());
            chatEntity.setType(ChatEntity.MessageType.CHAT);  // 항상 CHAT으로 설정

            // 보낸 사람 정보 설정
            MemberEntity sender = memberRepository.findByUsername(chatDto.getSender())
                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + chatDto.getSender()));
            chatEntity.setSender(sender);

            chatRepository.save(chatEntity);
            log.info("메시지 저장 완료: {}", chatEntity);  // 로깅 추가

            // 응답 DTO 설정
            chatDto.setOriginalSender(chatDto.getSender());  // 원래 username 저장
            chatDto.setSender(sender.getNickname());  // 표시용 닉네임
            chatDto.setType(ChatDto.MessageType.CHAT);
            chatDto.setSendTime(chatEntity.getSendTime());

            log.info("채팅 메시지 처리 완료: {}", chatDto);  // 로그 추가
            return chatDto;

        } catch (Exception e) {
            log.error("메시지 처리 중 오류 발생", e);  // 에러 로깅
            throw e;
        }
    }

    private ChatDto createAndSaveEventMessage(String username, ChatDto.MessageType type, String action) {
        MemberEntity member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + username));

        String content = member.getNickname() + action;

        // 메시지 저장
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setContent(content);
        chatEntity.setType(ChatEntity.MessageType.valueOf(type.name()));
        chatEntity.setSender(member);
        chatRepository.save(chatEntity);

        // DTO 생성
        return ChatDto.builder()
                .type(type)
                .sender(member.getNickname())
                .originalSender(member.getUsername())
                .content(content)
                .sendTime(LocalDateTime.now())
                .build();
    }

    @MessageMapping("/chat.join")
    @SendTo("/topic/public")
    public ChatDto join(@Payload ChatDto chatDto, SimpMessageHeaderAccessor headerAccessor) {
        log.info("입장 메시지 수신: {}", chatDto);
        headerAccessor.getSessionAttributes().put("username", chatDto.getSender());
        return createAndSaveEventMessage(chatDto.getSender(), ChatDto.MessageType.JOIN, "님이 입장하셨습니다.");
    }

    @MessageMapping("/chat.leave")
    @SendTo("/topic/public")
    public ChatDto leave(@Payload ChatDto chatDto) {
        log.info("퇴장 메시지 수신: {}", chatDto);
        return createAndSaveEventMessage(chatDto.getSender(), ChatDto.MessageType.LEAVE, "님이 퇴장하셨습니다.");
    }

    // MessageType 변환 헬퍼 메서드
    private ChatDto.MessageType convertMessageType(ChatEntity.MessageType entityType) {
        return ChatDto.MessageType.valueOf(entityType.name());
    }

    @GetMapping("/api/chat/history")
    @ResponseBody
    public List<ChatDto> getChatHistory() {
        log.info("채팅 기록 요청 수신");
        List<ChatEntity> messages = chatRepository.findTop100ByOrderBySendTimeDesc();
        return messages.stream()
                .map(message -> {
                    return ChatDto.builder()
                            .sender(message.getSender().getNickname())
                            .originalSender(message.getSender().getUsername())
                            .content(message.getContent())
                            .type(convertMessageType(message.getType()))
                            .sendTime(message.getSendTime())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/app/chat.leave/notify")
    @ResponseBody
    public void handleLeaveNotification(@RequestBody ChatDto chatDto) {
        log.info("브라우저 종료 감지 - 퇴장 메시지 수신: {}", chatDto);
        try {
            ChatDto leaveMessage = createAndSaveEventMessage(
                    chatDto.getSender(),
                    ChatDto.MessageType.LEAVE,
                    "님이 퇴장하셨습니다."
            );
            simpMessagingTemplate.convertAndSend("/topic/public", leaveMessage);
        } catch (Exception e) {
            log.error("퇴장 메시지 처리 중 오류 발생", e);
        }
    }
}
