package com.kamegatze.learnjvm.controllers.home;

import com.kamegatze.learnjvm.model.articles.Article;
import com.kamegatze.learnjvm.servicies.articles.ArticlesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class HomeController {

    private final ArticlesService articlesService;

    public HomeController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @GetMapping("/home")
    String handlingHomePage() {return "home";}

    @GetMapping("/articles")
    String handlingArticles(Model model) {
        model.addAttribute("articles", articlesService.findAll().stream().filter (Article::getPublished));
        return "articles";
    }

    @GetMapping("/courses")
    String handlingCourses() {return "courses";}

}
