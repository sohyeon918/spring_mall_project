package com.javateam.project.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MemberInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("member interceptor");
		boolean result = false;
		
		String errMsg = "";
		HttpSession session = request.getSession();
		
		// 로그인 인증 없이 접근
		if (session.getAttribute("LOGIN_USER") == null) {
			
			log.info("로그인 인증 안됨");
			
			// 에러 메시지
			errMsg = "접근을 위해서는 먼저 로그인 하셔야 됩니다";
			
			request.setAttribute("err_msg", errMsg);
			request.setAttribute("move_path", "login/login_form");
			
			// 에러 페이지 => 로그인 페이지로 이동
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/error/error.jsp");
			rd.forward(request, response);
			
			result = false;		
			
		} else { // 로그인 인증 후 접근
			
			log.info("로그인 인증");
			result = true;
		} //
		
		return result;
	}
	
}
