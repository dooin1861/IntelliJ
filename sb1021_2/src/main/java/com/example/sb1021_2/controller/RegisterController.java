package com.example.sb1021_2.controller;

import com.example.sb1021_2.entity.Member;
import com.example.sb1021_2.spring.DuplicateMemberException;
import com.example.sb1021_2.spring.MemberRegisterService;
import com.example.sb1021_2.spring.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {

	@Autowired
	private MemberRegisterService memberRegisterService;

	@GetMapping("/")
	public String root() {
		return "register/step1";
	}

	@RequestMapping("/register/step1")
	public String handleStep1(Model model) {
		List<Member> members = memberRegisterService.findAllMembers();
		model.addAttribute("members", members);
		model.addAttribute("registerRequest", new RegisterRequest()); // 모델에 추가
		return "register/step1";
	}

	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
			Model model) {
		if (!agree) {
			return "register/step1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}

	@GetMapping("/register/step2")
	public String handleStep2Get(Model model) {
		model.addAttribute("registerRequest", new RegisterRequest());
		return "redirect:/register/step1";
	}

	@PostMapping("/register/step3")
	public String handleStep3(RegisterRequest regReq) {
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (DuplicateMemberException ex) {
			return "register/step2";
		}
	}
}
