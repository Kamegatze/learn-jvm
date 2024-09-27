package com.kamegatze.learnjvm.controllers.home.articles

import com.kamegatze.learnjvm.model.db.articles.Article
import com.kamegatze.learnjvm.model.db.articles.Chapter
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/main/articles")
class ArticlesController {

    @GetMapping("/create")
    fun handlingCreateArticleView(model: Model): String {
        model.addAttribute("article", Article("",
                listOf(Chapter("", ""))
            )
        )
        return "/articles/create"
    }

    @PostMapping("/create")
    fun handlingCreateArticle(@ModelAttribute("article") @Valid article: Article): String {
        return "redirect:article"
    }
}