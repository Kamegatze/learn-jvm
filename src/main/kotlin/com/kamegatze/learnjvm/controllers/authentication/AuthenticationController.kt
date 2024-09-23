package com.kamegatze.learnjvm.controllers.authentication

import com.kamegatze.learnjvm.model.authentication.Login
import com.kamegatze.learnjvm.model.registration.Registration
import com.kamegatze.learnjvm.servicies.authentication.AuthenticationService
import com.kamegatze.learnjvm.servicies.authentication.exceptions.NotEqualsPasswordAndRetryPasswordException
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

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
        model.addAttribute("registration", Registration("", "", "", "", ""))
        return "authentication/registration"
    }

    @PostMapping("/register")
    fun handlingRegister(@ModelAttribute("registration") @Valid registration: Registration,
                         bindingResult: BindingResult, model: Model
    ): String {
        if (bindingResult.hasErrors()) {
            return "authentication/registration"
        }

        try {
            authenticationService.registration(registration)
        } catch (e: NotEqualsPasswordAndRetryPasswordException) {
            model.addAttribute("errorRetryPassword", e.message)
            return "authentication/registration"
        }
        return "redirect:/authentication/login"
    }
}