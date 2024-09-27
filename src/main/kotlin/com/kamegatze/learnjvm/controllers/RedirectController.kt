package com.kamegatze.learnjvm.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class RedirectController {
    @GetMapping("/")
    fun handlingRedirectToMainPage() = "redirect:main/home"

    @RequestMapping(method = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE],
        path = ["/main/**", "/account/**"])
    fun handlingRedirectToNotFoundPageApplication() = "/error/not-found"

    @RequestMapping(method = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE],
        path = ["/authentication/**"])
    fun handlingRedirectToNotFoundPageAuthentication() = "/error/authentication/not-found"
}