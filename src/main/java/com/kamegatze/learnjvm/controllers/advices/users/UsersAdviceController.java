package com.kamegatze.learnjvm.controllers.advices.users;

import com.kamegatze.learnjvm.configuration.security.details.UserDetails;
import com.kamegatze.learnjvm.model.db.users.Users;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice(annotations = Controller.class)
public class UsersAdviceController {

	@ModelAttribute("user")
	public Optional<Users> getUserViaAuthentication() {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof UsernamePasswordAuthenticationToken authenticationToken) {
			return Optional.ofNullable(((UserDetails) authenticationToken.getPrincipal()).getUser());
		} else {
			return Optional.empty();
		}
	}

}