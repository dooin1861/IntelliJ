package com.example.sb1023_3.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Respondent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 기본 키

	private int age;
	private String location;

	@OneToMany(mappedBy = "res", cascade = CascadeType.ALL)
	private List<AnsweredData> answeredDataList; // 응답자의 여러 답변

	// 기본 생성자
	public Respondent() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<AnsweredData> getAnsweredDataList() {
		return answeredDataList;
	}

	public void setAnsweredDataList(List<AnsweredData> answeredDataList) {
		this.answeredDataList = answeredDataList;
	}
}
