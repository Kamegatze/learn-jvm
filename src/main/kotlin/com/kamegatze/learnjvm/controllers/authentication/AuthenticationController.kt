package com.kamegatze.learnjvm.controllers.authentication

import com.kamegatze.learnjvm.model.authentication.Login
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/authentication")
class AuthenticationController {

    @GetMapping("/login")
    fun handlingLoginPage(model: Model): String {
        model.addAttribute("login", Login("", ""))
        return "authentication/login"
    }
}