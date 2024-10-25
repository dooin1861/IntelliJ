package com.example.sb1015_2;

import com.example.sb1015_2.entity.MyData;
import com.example.sb1015_2.repository.MyDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class Sb10152Application {

//	@Autowired // Autowired는 필드에 삽입하는 것을 권장하지 않는다.

	final MyDataRepository repository; // 성능 면에서는 이쪽이 더 나음.

//	public Sb10152Application(MyDataRepository repository) {
//		this.repository = repository;
//	} // 생성자 주입 방식. @Autowired를 대신 할 수 있음.

	public static void main(String[] args) {
		SpringApplication.run(Sb10152Application.class, args);
	}

	@PostConstruct
	public void init() {
		MyData d1 = new MyData();
		d1.setName("kim");
		d1.setAge(123);
		d1.setMail("kim@gilbut.co.kr");
		d1.setMemo("this is my data!");
		repository.saveAndFlush(d1);
		MyData d2 = new MyData();
		d2.setName("lee");
		d2.setAge(15);
		d2.setMail("lee@flower");
		d2.setMemo("my girl friend");
		repository.saveAndFlush(d2);

		MyData d3 = new MyData();
		d3.setName("choi");
		d3.setAge(37);
		d3.setMail("choi@happy");
		d3.setMemo("my work friend");
		repository.saveAndFlush(d3);
	}

}
