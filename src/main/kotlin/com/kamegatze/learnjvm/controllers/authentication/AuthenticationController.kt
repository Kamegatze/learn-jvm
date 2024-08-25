package com.kamegatze.learnjvm.controllers.authentication

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/authentication")
class AuthenticationController {

    @GetMapping("/login")
    fun handlingLoginPage(): String = "login"


}