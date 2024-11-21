//package com.example.demo.controller;
//
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.nio.file.AccessDeniedException;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    // AccessDeniedException을 처리하는 메서드
//    @ExceptionHandler(AccessDeniedException.class)
//    public String handleAccessDenied(AccessDeniedException ex, Model model) {
//        // 메시지 전달
//        model.addAttribute("alertMessage", "로그인 후 수정할 수 있습니다.");
//        return "board/boardDetail";  // 경고 메시지를 띄울 페이지로 반환
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception ex, Model model) {
//        // 일반 예외 처리
//        model.addAttribute("alertMessage", "예기치 못한 오류가 발생했습니다.");
//        return "board/boardDetail";  // 경고 메시지를 띄울 페이지로 반환
//    }
//}