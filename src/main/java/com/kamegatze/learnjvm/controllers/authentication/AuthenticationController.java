package com.kamegatze.learnjvm.controllers.authentication;

import com.kamegatze.learnjvm.model.authentication.Login;
import com.kamegatze.learnjvm.model.registration.Registration;
import com.kamegatze.learnjvm.servicies.authentication.AuthenticationService;
import com.kamegatze.learnjvm.servicies.authentication.exceptions.NotEqualsPasswordAndRetryPasswordException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    String handlingLoginPage(Model model) {
        model.addAttribute("login", new Login("", ""));
        return "authentication/login";
    }

    @GetMapping("/register")
    String handlingRegisterPage(Model model) {
        model.addAttribute("registration", new Registration("", "", "", "", ""));
        return "authentication/registration";
    }

    @PostMapping("/register")
    String handlingRegister(@ModelAttribute("registration") @Valid Registration registration,
                            BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "authentication/registration";
        }

        try {
            authenticationService.registration(registration);
        } catch (NotEqualsPasswordAndRetryPasswordException e) {
            model.addAttribute("errorRetryPassword", e.getMessage());
            return "authentication/registration";
        }
        return "redirect:/authentication/login";
    }

}
