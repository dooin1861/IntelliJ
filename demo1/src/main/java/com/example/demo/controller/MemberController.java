package com.example.demo.controller;

import com.example.demo.member.service.ChangePasswordService;
import com.example.demo.member.service.WrongIdPasswordException;
import com.example.demo.password.PasswordChangeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/edit")
public class MemberController {
    private final ChangePasswordService changePasswordService;

    // 비밀번호 변경 폼을 보여주는 GET 요청
    @GetMapping("/changePwdForm")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("passwordChangeForm", new PasswordChangeForm()); // 폼 객체 모델에 추가
        return "edit/changePwdForm"; // changePwdForm.html을 반환
    }

    // 비밀번호 변경을 처리하는 POST 요청
    @PostMapping("/changePwdForm")
    public String changePassword(@ModelAttribute PasswordChangeForm passwordChangeForm, Model model) {
        // 새 비밀번호와 확인 비밀번호가 일치하는지 확인
        if (!passwordChangeForm.getNewPassword().equals(passwordChangeForm.getConfirmPassword())) {
            model.addAttribute("message", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            return "edit/changePwdForm"; // 에러 메시지와 함께 폼으로 돌아가기
        }

        try {
            changePasswordService.changePassword(
                    passwordChangeForm.getEmail(),
                    passwordChangeForm.getOldPassword(),
                    passwordChangeForm.getNewPassword()
            );
            model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        } catch (WrongIdPasswordException e) {
            model.addAttribute("message", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("message", "비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
        }
        return "edit/changePwdForm"; // 비밀번호 변경 결과를 보여주기 위한 페이지로 이동
    }
}
