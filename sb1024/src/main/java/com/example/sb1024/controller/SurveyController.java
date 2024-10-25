package com.example.sb1024.controller;

import com.example.sb1024.entity.AnsweredData;
import com.example.sb1024.survey.Question;
import com.example.sb1024.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	SurveyService surveyService;

	@GetMapping("/surveyForm")  // 이름은 아무렇게나 지정해도 상관없다
	public String form(Model model) {
		List<Question> questions = createQuestions();
		for (Question question : questions) {
			System.out.println(question);
		}
		model.addAttribute("questions", questions);
		return "/survey/surveyForm"; // 리턴만 잘하면 됨
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
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		surveyService.save(data);
		return "/survey/submitted";
	}

}
