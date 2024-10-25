package com.example.sb1010_2;

import com.example.sb1010_2.aop2.Greet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sb10102Application {

    @Autowired
    Greet greet;

    public static void main(String[] args) {
        SpringApplication.run(Sb10102Application.class, args).getBean(Sb10102Application.class).execute();
    }

    private void execute() {
        greet.greeting();
    }

}
