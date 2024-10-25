package com.example.sb1023_3.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 기본 키 추가

	private String title;

	@ElementCollection // List<String>을 위한 애노테이션
	private List<String> options = Collections.emptyList(); // 초기값 설정

	// 기본 생성자
	public Question() {}

	public Question(String title, List<String> options) {
		this.title = title;
		this.options = options;
	}

	public Question(String title) {
		this(title, Collections.emptyList());
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}
}
