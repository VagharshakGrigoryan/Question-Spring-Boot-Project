package com.example.register.security;

import com.example.register.model.User;
import com.example.register.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private final UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		super.onAuthenticationSuccess(request, response, authentication);
		
		User user = userService.getUser(authentication.getName());
		user.setLastActive(LocalDateTime.now());
		user = userService.update(user);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("userFirstName", user.getFirstName());
	}

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		return "/home";
	}
}
