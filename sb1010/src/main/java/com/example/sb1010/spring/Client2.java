package com.example.sb1010.spring;

public class Client2 {

    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    public void connect() {
        System.out.println("Client2.connect() called");
    }

    public void send() {
        System.out.println("Client.send() to " + host);
    }

    public void close() {
        System.out.println("Client2.close() called");
    }
}
