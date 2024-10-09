package com.kamegatze.learnjvm.controllers.home

import com.kamegatze.learnjvm.servicies.articles.ArticlesService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/main")
class HomeController(
    private val articlesService: ArticlesService
) {

    @GetMapping("/home")
    fun handlingHomePage() = "home"

    @GetMapping("/articles")
    fun handlingArticles(model: Model): String {
        model.addAttribute("articles", articlesService.findAll().filter { it.published!! })
        return "articles"
    }

    @GetMapping("/courses")
    fun handlingCourses() = "courses"

}