package com.example.sb1120;

import com.example.sb1120.repository.Member;
import com.example.sb1120.repository.MemberRepository;
import com.example.sb1120.repository.Team;
import com.example.sb1120.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sb1120Application {

    public static void main(String[] args) {
        SpringApplication.run(Sb1120Application.class, args);
    }

}
