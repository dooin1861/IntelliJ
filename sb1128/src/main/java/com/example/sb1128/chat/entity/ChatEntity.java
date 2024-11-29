package com.example.sb1128.chat.entity;

import com.example.sb1128.member.entity.MemberEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "chat")
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity sender;

    @Column(name = "send_time", nullable = false)
    private LocalDateTime sendTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MessageType type = MessageType.CHAT;  // 기본값 설정

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    @PrePersist
    public void prePersist() {
        this.sendTime = LocalDateTime.now();
        if (this.type == null) {
            this.type = MessageType.CHAT;
        }
    }
}