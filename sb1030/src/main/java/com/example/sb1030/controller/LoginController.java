package com.example.sb1030.controller;

import javax.servlet.http.HttpSession;

import com.example.sb1030.spring.AuthInfo;
import com.example.sb1030.spring.AuthService;
import com.example.sb1030.spring.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthService authService;

	@GetMapping
	public String form(edu.du.sb1030.controller.LoginCommand loginCommand) {
		System.out.println("-----------------여기");
		return "/login/loginForm";
	}

	@PostMapping
	public String submit(edu.du.sb1030.controller.LoginCommand loginCommand, Errors errors, HttpSession session) {

		new LoginCommandValidator().validate(loginCommand, errors);
		if (errors.hasErrors()) {
			return "/login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenticate(
					loginCommand.getEmail(),
					loginCommand.getPassword());
			session.setAttribute("authInfo", authInfo);
			System.out.println(authInfo.getName() + " 세션 저장!");
			return "/login/loginSuccess";
		} catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "/login/loginForm";
		}
	}
}