package com.example.sb1007;

import com.example.sb1007.dao.ISimpleBbsDao;
import com.example.sb1007.dto.SimpleBbsDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @Autowired
    ISimpleBbsDao dao;

    @GetMapping("/")
    public String root() {

        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute ("list", dao.listDao());
        return "list";
    }

    @GetMapping("/view")
    public String view(HttpServletRequest request, Model model) {
        String sId = request.getParameter("id");
        model.addAttribute("dto", dao.viewDao(sId));
        return "view";
    }

    @GetMapping("/writeForm")
    public String writeForm() {

        return "writeForm";
    }

    @PostMapping("/write")
    public String write(HttpServletRequest request) {
        dao.writeDao(request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("content"));
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        dao.deleteDao(request.getParameter("id"));
        return "redirect:list";
    }

    @GetMapping("/updateForm")
    public String update(Model model, String id) {
        model.addAttribute("dto", dao.viewDao(id));
        return "updateForm";
    }

    @PostMapping("/update")
    public String update(@RequestParam String id,
                         @RequestParam String writer,
                         @RequestParam String title,
                         @RequestParam String content) {
        dao.updateDao(id, writer, title, content);
        return "redirect:/list";
    }
}
