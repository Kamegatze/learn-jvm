package com.kamegatze.learnjvm.controllers.home

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/main")
class HomeController {

    @GetMapping("/home")
    fun handlingHomePage() = "home"

    @GetMapping("/articles")
    fun handlingArticles() = "articles"

    @GetMapping("/courses")
    fun handlingCourses() = "courses"

}