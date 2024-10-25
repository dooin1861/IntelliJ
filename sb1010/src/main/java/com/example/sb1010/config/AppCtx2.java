package com.example.sb1010.config;

import com.example.sb1010.spring.Client;
import com.example.sb1010.spring.Client2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx2 {

    @Bean
//    @Scope("singleton") 없어도 기본적으로 싱글톤 적용
    public Client client() {
        Client client = new Client();
        client.setHost("host");
        return client;
    }

    @Bean (destroyMethod = "close")
    public Client2 client2() {
        Client2 client = new Client2();
        client.setHost("host");
        client.connect();
        return client;
    }
}
