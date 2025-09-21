package io.github.app.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalRequestAttributes {

	@ModelAttribute("contextPath")
	public String contextPath(HttpServletRequest request) {
		return request.getRequestURI();
	}
}
