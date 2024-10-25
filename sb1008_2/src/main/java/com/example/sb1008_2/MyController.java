package com.example.sb1008_2;

import com.example.sb1008_2.dao.ArticleDao;
import com.example.sb1008_2.model.Article;
import com.example.sb1008_2.model.ArticleListModel;
import com.example.sb1008_2.service.ArticleNotFoundException;
import com.example.sb1008_2.service.ListArticleService;
import com.example.sb1008_2.service.ReadArticleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {

    @Autowired
    ListArticleService listSerivce;

    @Autowired
    ReadArticleService readSerivce;

    @Autowired
    ArticleDao dao;

    @GetMapping("/")
    public String index() {
        return "redirect:list";
    }

    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        String pageNumberString = request.getParameter("p");
        int pageNumber = 1;
        if (pageNumberString != null && pageNumberString.length() > 0) {
            pageNumber = Integer.parseInt(pageNumberString);
        }
        ArticleListModel articleListModel = listSerivce.getArticleList(pageNumber);
        model.addAttribute("listModel", articleListModel);

        if (articleListModel.getTotalPageCount() > 0) {
            int beginPageNumber =
                    (articleListModel.getRequestPage() - 1) / 10 * 10 + 1;
            int endPageNumber = beginPageNumber + 9;
            if (endPageNumber > articleListModel.getTotalPageCount()) {
                endPageNumber = articleListModel.getTotalPageCount();
            }
            model.addAttribute("beginPage", beginPageNumber);
            model.addAttribute("endPage", endPageNumber);
        }
        return "list_view";
    }

    @GetMapping("/read")
    public String read(Model model, HttpServletRequest request) {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        String viewPage = null;
        try {
            Article article = readSerivce.readArticle(articleId);
            model.addAttribute("article", article);
            viewPage = "/read_view.jsp";
        } catch(ArticleNotFoundException ex) {
            viewPage = "/article_not_found.jsp";
        }
        return "read_view";
    }

    @GetMapping("/writeForm")
    public String writeForm() {

        return "writeForm";
    }

    @PostMapping ("/write")
    public String write(HttpServletRequest request) {
        dao.writeDao(request.getParameter("writerName"),
                request.getParameter("password"),
                request.getParameter("title"),
                request.getParameter("content"));
        return "redirect:list_view";
    }
}
