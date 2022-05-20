package com.javateam.project.interceptor;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.javateam.project.domain.MemberVO;
import com.javateam.project.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AdminInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	MemberService memberService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("admin interceptor");
		// 관리자 점검(인증)
		// 세션 => 로그인 점검
		// 롤(role) => "admin"
		
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
			
			// 롤(role) 점검 => 관리자(admin)
			String id = ((MemberVO)session.getAttribute("LOGIN_USER")).getId(); 
			String role = memberService.getRole(id);
			
			if (!role.equals("admin")) { // 관리자가 아님
				
				log.info("관리자 권한이 아님(일반 권한)");
				
				// response.sendError(403);
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				result = false;
				
			} else { // 관리자
				
				log.info("관리자 권한으로 로그인 인증");
				result = true;
				
			} //
			
		} //
		
		return result;
		// return super.preHandle(request, response, handler);
	}
	
}