package co.kr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ServiceWorkerController {
	private static final Logger logger = LoggerFactory.getLogger(ServiceWorkerController.class);
	
	@RequestMapping("/")
	public String index() {
		logger.info("인덱스 컨트롤러");
		return "home";
	}
}
