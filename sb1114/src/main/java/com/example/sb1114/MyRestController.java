package com.example.sb1114;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@RestController
public class MyRestController {

    @GetMapping("/hello")
    public LocalDateTime hello() {
        return LocalDateTime.now();
    }

    @GetMapping("/Account/{name}")
    public String Account(@PathVariable String name) {
        log.info(name);
        return name;
    }

//    @PostMapping("/account")
//    public Account account(@RequestBody Account account) {
//        log.info(account.toString());
//        return account;
//    }

    @PostMapping("/account")
    public ResponseEntity<?> account(@RequestBody Account account) {
        log.info(account.toString());
        return ResponseEntity.notFound().build();
    }
}
