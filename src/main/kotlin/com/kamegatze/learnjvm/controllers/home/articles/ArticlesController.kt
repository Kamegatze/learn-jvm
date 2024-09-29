package com.kamegatze.learnjvm.controllers.home.articles

import com.kamegatze.learnjvm.configuration.security.details.UsersDetails
import com.kamegatze.learnjvm.model.db.articles.Article
import com.kamegatze.learnjvm.model.db.articles.Chapter
import com.kamegatze.learnjvm.servicies.articles.ArticlesService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@Controller
@RequestMapping("/articles")
class ArticlesController(private val articlesService: ArticlesService) {

    @GetMapping("/create")
    fun handlingCreateArticleView(model: Model): String {
        model.addAttribute("article", Article("",
                listOf(Chapter("", ""))
            )
        )
        return "/articles/create"
    }

    @PostMapping("/create")
    fun handlingCreateArticle(@ModelAttribute("article") article: Article, authentication: Authentication): String {
        article.userId = (authentication.principal as UsersDetails).user.id;
        articlesService.save(article)
        return "redirect:/main/articles"
    }

    @PostMapping("/create/addition-chapter")
    fun handlingAdditionChapter(@ModelAttribute("article") article: Article): String {
        article.chapters = listOf(*article.chapters!!.toTypedArray(), Chapter("", ""))
        return "/articles/form-chapters"
    }

    @GetMapping("/view/{id}")
    fun handlingGetById(@PathVariable id: UUID, model: Model): String {
        model.addAttribute("article", articlesService.getById(id))
        return "articles/view"
    }
}