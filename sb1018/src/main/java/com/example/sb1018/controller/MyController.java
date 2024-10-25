package com.example.sb1018.controller;

import com.example.sb1018.entity.Emp;
import com.example.sb1018.repository.MyDataRepository;
import com.example.sb1018.service.EmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final EmService emService;

    @GetMapping("/") // 요청 URL에 맞는 매핑
    public String listAll(Model model) {
        List<Emp> empList = emService.findAllEmps(); // 서비스 호출
        model.addAttribute("employees", empList); // 모델에 추가
        model.addAttribute("emp", new Emp());
        return "index"; // 뷰 이름 반환
    }

    @GetMapping("/edit/{empno}")
    public String edit(@PathVariable("empno") Integer empNo, Model model) {
        // 특정 직원 정보 가져오기
        Emp emp = emService.findByEmpno(empNo); // 해당 메서드는 이전에 구현한 것
        model.addAttribute("emp", emp);

        return "edit"; // edit.html 템플릿 반환
    }


    @PostMapping("/edit")
    public String update(@RequestParam int empNo, @RequestParam String empName, Model model) {
        Emp updatedEmp = emService.updateEmp(empNo, empName);
        model.addAttribute("emp", updatedEmp);
        return "redirect:/"; // 업데이트 후 직원 목록 페이지로 리다이렉트
    }
}
