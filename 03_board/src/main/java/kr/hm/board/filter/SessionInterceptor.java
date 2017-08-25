package kr.hm.board.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println("Interceptor : PreHandle");

		// Session userid check
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		// Login false
		if (null == userId) {
//			System.out.println("Interceptor : Session Check Fail");
//			System.out.println(userId);
			// main page 로 이동
			response.sendRedirect("/board/user/login.do");
			return false;
		}
		// Login true
		else {
//			System.out.println(userId);
//			System.out.println("Interceptor : Session Check true");
			return super.preHandle(request, response, handler);

		}
	}

}
