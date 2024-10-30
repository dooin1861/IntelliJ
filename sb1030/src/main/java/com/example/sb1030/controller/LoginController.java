package com.example.sb1030.controller;

import javax.servlet.http.HttpSession;

import com.example.sb1030.spring.AuthInfo;
import com.example.sb1030.spring.AuthService;
import com.example.sb1030.spring.WrongIdPasswordException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	private AuthService authService;
		
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
		
	@GetMapping
	public String form(LoginCommand loginCommand) {
		return "login/loginForm";
	}
		
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session) {
		new LoginCommandVaildator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenticate(
					loginCommand.getEmail(),
					loginCommand.getPassword());
		
			session.setAttribute("authInfo", authInfo);
			System.out.println(authInfo.getName() + "세션 저장");
			return "login/loginSuccess";
		} catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	} 	
}