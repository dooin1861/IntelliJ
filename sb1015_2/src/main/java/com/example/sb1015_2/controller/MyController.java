package com.example.sb1015_2.controller;


import com.example.sb1015_2.entity.MyData;
import com.example.sb1015_2.repository.MyDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MyController {
    final MyDataRepository repository; // Autowired 안쓰고 주입하기

    @GetMapping("/")
    public String index(
            @ModelAttribute("formModel") MyData mydata, Model model) {
        model.addAttribute("msg", "this is sample content");
        List<MyData> list= repository.findAll();
        model.addAttribute("datalist", list);
        return "index";
    }

    @PostMapping("/")
    public String form(MyData mydata) {
        repository.save(mydata); // saveAndFlush로 써도 ok.
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute MyData myData, Model model) {
        Optional<MyData> myData1 = repository.findById(id);
        model.addAttribute("formModel", myData1.get());
        return "edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute MyData myData) {
        repository.save(myData);
        return "redirect:/";
    }

    @PostMapping("delete")
    public String delete(@ModelAttribute MyData myData) {
        repository.delete(myData);
        return "redirect:/";
    }
}
