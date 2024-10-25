package com.example.sb1024.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnsweredData {

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private int id;

	@ElementCollection
	@CollectionTable( 							 // 테이블 생성 애노테이션
			name = "responses", 					// responses 라는 테이블을 생성
			joinColumns = @JoinColumn(name = "MEMBER_ID") // Member_Id를 통해 다른 테이블과 조인
	)
	@OrderColumn			// 리스트의 순서를 유지하기 위해 사용 (1, 2, 3, A, B, C 같은..)
	@Column(name = "seq")   // responses에 seq라는 칼럼 생성
	private List<String> responses;

	@OneToOne
	@JoinColumn(name = "RESPONDENT_ID")
	private Respondent res;
//
//	public List<String> getResponses() {
//		return responses;
//	}
//
//	public void setResponses(List<String> responses) {
//		this.responses = responses;
//	}
//
//	public Respondent getRes() {
//		return res;
//	}
//
//	public void setRes(Respondent res) {
//		this.res = res;
//	}

}
