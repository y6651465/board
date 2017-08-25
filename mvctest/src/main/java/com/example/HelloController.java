package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping("/")
	public void index () {
		logger.info("컨트롤러 까지 는 들어옴");
	}
	@RequestMapping("/main")
	public void hello(Model model) {
	}
	@RequestMapping("/registerForm")
	public void registerForm() {
	}
}
