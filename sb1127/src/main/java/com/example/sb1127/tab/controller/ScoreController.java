package com.example.sb1127.tab.controller;

import com.example.sb1127.tab.dto.NoteRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @GetMapping("/create")
    public String createScorePage(Model model) {
        return "score/create";
    }

    @PostMapping("/save")
    public String saveScore(@RequestParam String title) {
        // 저장 로직 구현
        return "redirect:/score/create";
    }

    @PostMapping("/add-note")
    @ResponseBody
    public String addNote(@RequestBody NoteRequest request) {
        // 노트 추가 로직 구현
        return "success";
    }
}