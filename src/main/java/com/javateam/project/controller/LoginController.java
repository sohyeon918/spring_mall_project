package com.javateam.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javateam.project.domain.RoleVO;
import com.javateam.project.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	MemberService memberService;

	@RequestMapping("login_form")
	public ModelAndView loginForm() {
		
		ModelAndView mv = new ModelAndView();
		log.info("loginForm");
		
		mv.setViewName("/member/login");
		
		return mv;
	}
	
	// @PostMapping("login_proc")
	@RequestMapping(value="login_proc", method=RequestMethod.POST)
	public String loginProc(@RequestParam("id") String id, 
							@RequestParam("pwd") String pwd,
							Model model,
							HttpSession session,
							RedirectAttributes ra) {
		
		log.info("로그인 처리 : 아이디 {}, 패쓰워드 {}", id, pwd);
		String page = "";
		
		// 탈퇴한 회원 여부 점검(useyn='n')
		
		// 탈퇴한 회원이 로그인할 경우
		if (memberService.isActiveMember(id) == false) {
			
			// 가입 페이지로 이동
			model.addAttribute("err_msg", "예전에 탈퇴한 회원입니다.");
			model.addAttribute("move_path", "/member/join_form");
			page = "/error/error";
			
		} else { // 활동중인 회원 로그인
		
			String msg = memberService.checkLogin(id, pwd);
			
			if (msg.equals("아이디/패쓰워드가 모두 일치합니다.")) {
				
				// 세션 변수 생성(로그인 인증) : MemberVO => Session
				session.setAttribute("LOGIN_USER", memberService.getMember(id));
				
				// 세션 변수 생성(롤(role) 인증) : RoleVO => Session
				session.setAttribute("LOGIN_ROLE_USER", new RoleVO(id, memberService.getRole(id)));
				
				page = "redirect:/"; 
				
			} else if (msg.equals("패쓰워드가 일치하지 않습니다.")) {
				
				ra.addFlashAttribute("login_err_msg", msg);
				// 로그인 페이지 이동
				page = "redirect:/login/login_form";
				
			} else { // "회원 아이디가 존재하지 않습니다."
				
				ra.addFlashAttribute("login_err_msg", msg);
				// 회원 가입 페이지 이동
				page = "redirect:/member/contract";
			}
			
		} //	
		
		return page;
	} //
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		
		log.info("로그아웃");
		
		// 모든 세션 제거
		session.invalidate();
		
		return "redirect:/";
	}
	
}
