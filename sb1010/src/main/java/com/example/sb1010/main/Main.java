package com.example.sb1010.main;

import com.example.sb1010.config.AppCtx2;
import com.example.sb1010.spring.Client;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppCtx2.class);

        Client client = ctx.getBean(Client.class);
        client.send();

        ctx.close();
    }
}
