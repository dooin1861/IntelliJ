package com.example.sb1030.controller;

import javax.servlet.http.HttpSession;

import com.example.sb1030.spring.AuthInfo;
import com.example.sb1030.spring.ChangePasswordService;
import com.example.sb1030.spring.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {

    @Autowired
    private ChangePasswordService changePasswordService;

    @GetMapping
    public String form(@ModelAttribute ChangePwdCommand pwdCmd, Model model) {
        model.addAttribute("passwordCommand", new ChangePwdCommand());
        return "/edit/changePwdForm";
    }

    @PostMapping
    public String submit(@ModelAttribute ChangePwdCommand pwdCmd,
                         Errors errors,
                         HttpSession session) {
        new ChangePwdCommandValidator().validate(pwdCmd, errors);
        if (errors.hasErrors()) {
            return "/edit/changePwdForm";
        }
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        try {
            changePasswordService.changePassword(
                    authInfo.getEmail(),
                    pwdCmd.getCurrentPassword(),
                    pwdCmd.getNewPassword());
            System.out.println("------------->" + pwdCmd.getNewPassword());
            return "/edit/changePwd";
        } catch (WrongIdPasswordException e) {
            errors.rejectValue("currentPassword", "notMatching");
            return "/edit/changePwdForm";
        }
    }
}
