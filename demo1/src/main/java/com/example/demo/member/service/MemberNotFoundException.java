package com.example.demo.member.service;

public class MemberNotFoundException extends RuntimeException {
    // 기본 생성자
    public MemberNotFoundException() {
        super("해당 이메일을 가진 사용자가 없습니다.");
    }

    // 메시지를 전달받을 수 있는 생성자
    public MemberNotFoundException(String message) {
        super(message);  // 부모 클래스(RuntimeException)에 메시지를 전달
    }
}
