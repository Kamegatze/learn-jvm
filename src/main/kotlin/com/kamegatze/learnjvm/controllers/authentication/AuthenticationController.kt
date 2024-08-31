package com.kamegatze.learnjvm.controllers.authentication

import com.kamegatze.learnjvm.model.authentication.Login
import com.kamegatze.learnjvm.model.db.users.Users
import com.kamegatze.learnjvm.servicies.authentication.AuthenticationService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Instant
import java.util.*

@Controller
@RequestMapping("/authentication")
class AuthenticationController(private val authenticationService: AuthenticationService) {

    @GetMapping("/login")
    fun handlingLoginPage(model: Model): String {
        model.addAttribute("login", Login("", ""))
        return "authentication/login"
    }

    @GetMapping("/register")
    fun handlingRegisterPage(model: Model): String {
        model.addAttribute("user", Users(null, "", "", "", "",
            null, null, null, null, null))
        return "authentication/registration"
    }

    @PostMapping("/register")
    fun handlingRegister(@ModelAttribute("user") user: Users): String {
        authenticationService.registration(user)
        return "redirect:/authentication/login"
    }
}