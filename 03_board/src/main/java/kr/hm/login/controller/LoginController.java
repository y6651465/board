package kr.hm.login.controller;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.hm.board.service.UserService;
import kr.hm.board.vo.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/user")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/login.do")
	public void login(UserVO user, HttpSession session, Locale locale) throws Exception {
		logger.info("login : "+locale);

	}

	@RequestMapping("/loginForm.do")
	public ModelAndView loginForm( UserVO user, HttpServletRequest req) throws Exception{
		UserVO login = userService.loginUser(user);
		ModelAndView mav = new ModelAndView();
		String msg = null;
		HttpSession session = req.getSession();
		if (login != null) {
			session.setAttribute("userId", login.getId());
		
			msg = "로그인 되셨습니다.";
			mav.setViewName("user/loginForm");
			mav.addObject("msg",msg);
		}
		else {
			mav.setViewName("user/login");
			msg = "아이디와 비밀번호를 확인해 주세요";
			mav.addObject("msg", msg);
			session.invalidate();
		}
		return mav;


	}
	@RequestMapping("/logout.do")
	public String logout(UserVO user, HttpSession session, HttpServletResponse res, HttpServletRequest req) {
		session.invalidate();
		return "user/login";
	}
	
	@RequestMapping("/register.do")
	public void userRegister () {
		
	}
	
	@RequestMapping("/registerForm.do")
	public void userRegisterForm(UserVO user) throws Exception {
		userService.register(user);
	}


}
