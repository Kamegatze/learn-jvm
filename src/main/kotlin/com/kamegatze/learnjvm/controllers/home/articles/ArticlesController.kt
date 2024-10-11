package com.kamegatze.learnjvm.controllers.home.articles

import com.kamegatze.learnjvm.configuration.security.details.UsersDetails
import com.kamegatze.learnjvm.model.articles.Article
import com.kamegatze.learnjvm.model.articles.Chapter
import com.kamegatze.learnjvm.servicies.articles.ArticlesService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

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
        return "redirect:/articles/all-articles-by-user"
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

    @GetMapping("/all-articles-by-user")
    fun handlingGetAllArticlesByUser(model: Model, authentication: Authentication): String {
        val user = (authentication.principal as UsersDetails).user
        val articlesByUser = articlesService.findAllByUser(user)
        model.addAttribute("articles", articlesByUser)
        model.addAttribute("searchName", "search")
        return "articles/all-articles-by-user"
    }

    @GetMapping("/edit/{id}")
    fun handlingEditArticle(@PathVariable id: UUID, model: Model): String {
        model.addAttribute("article", articlesService.getById(id))
        return "/articles/edit"
    }

    @PostMapping("/published/{id}")
    fun handlingPublishedArticle(@PathVariable id: UUID, published: Boolean): ResponseEntity<Void> {
        articlesService.updatePublished(published, id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/delete/{id}")
    fun handlingDeleteArticle(@PathVariable id: UUID): String {
        articlesService.delete(id)
        return "redirect:/articles/all-articles-by-user"
    }
}