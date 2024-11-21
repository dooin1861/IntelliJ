package com.example.demo.member.service;

public class WrongIdPasswordException extends RuntimeException {
    // 기본 생성자
    public WrongIdPasswordException() {
        super("잘못된 이메일 또는 비밀번호입니다.");
    }

    // 메시지를 전달받을 수 있는 생성자
    public WrongIdPasswordException(String message) {
        super(message);  // 부모 클래스(RuntimeException)에 메시지를 전달
    }
}
