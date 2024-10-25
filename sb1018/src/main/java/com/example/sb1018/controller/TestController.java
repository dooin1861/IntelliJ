package com.example.sb1018.controller;

import com.example.sb1018.entity.Emp;
import com.example.sb1018.service.EmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    final EmService emService;

    @GetMapping("/{empno}/{ename}")
    public Emp index(@PathVariable Integer empno, @PathVariable String ename) {
        return emService.updateEmp(empno, ename);
    }
}
