package com.example.sb1023_3.controller;

import java.util.Arrays;
import java.util.List;

import com.example.sb1023_3.entity.AnsweredData;
import com.example.sb1023_3.entity.Question;
import com.example.sb1023_3.entity.Respondent;
import com.example.sb1023_3.repository.AnsweredDataRepository; // 응답 데이터 리포지토리

import com.example.sb1023_3.repository.RespondentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	private RespondentRepository respondentRepository; // 응답자 리포지토리 주입

	@Autowired
	private AnsweredDataRepository answeredDataRepository; // 응답 데이터 리포지토리 주입

	@GetMapping("/")
	public String redirectToForm() {
		return "redirect:/survey";
	}

	@GetMapping
	public String form(Model model) {
		AnsweredData answeredData = new AnsweredData();
		answeredData.setRes(new Respondent()); // Respondent 객체 초기화
		model.addAttribute("ansData", answeredData);
		List<Question> questions = createQuestions();
		model.addAttribute("questions", questions);
		return "surveyForm";
	}


	private List<Question> createQuestions() {
		Question q1 = new Question("당신의 역할은 무엇입니까?",
				Arrays.asList("서버", "프론트", "풀스택"));
		Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?",
				Arrays.asList("이클립스", "인텔리J", "서브라임"));
		Question q3 = new Question("하고 싶은 말을 적어주세요.");
		return Arrays.asList(q1, q2, q3);
	}

	@PostMapping
	public String submit(@ModelAttribute("respondent") Respondent respondent,
						 @ModelAttribute("ansData") AnsweredData data) {
		// 응답자 정보 저장
		Respondent savedRespondent = respondentRepository.save(respondent);

		// 응답 데이터 설정
		data.setRes(savedRespondent); // 응답자와의 관계 설정
		answeredDataRepository.save(data); // 응답 데이터 저장

		return "submitted"; // 결과 페이지 반환
	}
}
