package com.example.sb1023_3.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "answered_data")
public class AnsweredData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 기본 키 추가

	@ElementCollection // List<String>을 위한 애노테이션
	private List<String> responses;

	@ManyToOne // Respondent와의 관계 설정
	@JoinColumn(name = "respondent_id") // 외래 키 설정
	private Respondent res;

	// 기본 생성자
	public AnsweredData() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getResponses() {
		return responses;
	}

	public void setResponses(List<String> responses) {
		this.responses = responses;
	}

	public Respondent getRes() {
		return res;
	}

	public void setRes(Respondent res) {
		this.res = res;
	}
}
