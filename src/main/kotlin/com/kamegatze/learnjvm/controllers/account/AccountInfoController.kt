package com.kamegatze.learnjvm.controllers.account

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/account")
class AccountInfoController {

    @GetMapping("/page_info")
    fun handlingAccount() = "account/main"

}