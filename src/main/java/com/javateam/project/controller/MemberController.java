package com.javateam.project.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javateam.project.domain.MemberDTO;
import com.javateam.project.domain.MemberVO;
import com.javateam.project.domain.RoleVO;
import com.javateam.project.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	MemberService memberService;

	@RequestMapping("contract")
	public String constract() {
		
		log.info("회원 가입 약관 동의 : contract");
		
		return "/member/contract";
	}
	
	@RequestMapping("join_form")
	public String joinForm(@RequestParam(value="contractOK", defaultValue="false") String contractOK) {
		
		log.info("회원 가입폼");
		String page = "";
		
		// 약관에 동의했으면...
		if (contractOK.equals("true")) {
			log.info("약관 동의 : " + contractOK);
			page="/member/join";
		} else {
			log.info("약관 미동의 : " + contractOK);
			// page="/member/contract"; // 약관 페이지 이동
			page="redirect:/member/contract"; // 약관 페이지(주소) 이동
		}
		
		return page;
	}
	
	@PostMapping("join_proc")
	public String joinProc(MemberVO memberVO) {
		
		log.info("회원 가입 처리 : " + memberVO);
		String page = "";
		
		memberVO.setUseyn("y"); // 회원(활동 여부 체크)
		
		RoleVO roleVO = new RoleVO();
		roleVO.setId(memberVO.getId());
		roleVO.setRole("user"); // 기본 role => 회원("user")
		
		// 회원 가입 성공
		if (memberService.insertMember(memberVO, roleVO) == true) {
			
			log.info("회원 가입 성공");
			page = "redirect:/login/login_form"; // 로그인
			
		} else {
			
			log.info("회원 가입 실패");
			page = "redirect:/member/join_form?contractOK=true"; // 회원가입폼
		}
		
		return page;
	} //
	
	@GetMapping("member_info")
	public String memberInfo() {
		
		log.info("개별 회원 정보 조회/수정/(탈퇴)");
		return "/member/member_info";
	} //
	
	@GetMapping("member_update")
	public String memberUpdate() {
		
		log.info("개별 회원 정보 수정");
		return "/member/member_update";
	}
	
	@PostMapping("update_proc")
	// public String updateProc(MemberVO memberVO) {
	public String updateProc(@Valid MemberDTO memberDTO, 
							 BindingResult result,
							 RedirectAttributes ra,
							 HttpSession session) {
		
		log.info("개별 회원 정보 수정 처리");
		String page = "";
		
		// 신규 패쓰워드간의 동등 여부 점검 
		log.info("신규 패쓰워드간의 동등 여부 점검  : " 
					+ memberDTO.getPwd().trim().contentEquals(memberDTO.getPwdCheck().trim()));
		
		String pwd = memberDTO.getPwd().trim();
		String pwdCheck = memberDTO.getPwdCheck().trim();
		
		// 공백이 아니고 폼점검 통과 => 신규 패쓰워드 저장
		if (!pwd.equals(pwdCheck)) {
			
			log.info("패쓰워드 불일치");
			ra.addFlashAttribute("pwd_err_msg", "패쓰워드가 일치하지 않습니다");
		}
		
		// 주소 필드 폼점검
		// 기준) 관련 세가지 필드가 모두 공백이거나 모두 입력되어 있어야 유효한 데이터
		
		// 우편번호/기본주소 입력되었지만 상세주소가 입력되지 않았을 때
		if (!memberDTO.getZipNum().equals("") &&
			!memberDTO.getAddress1().equals("") &&
		     memberDTO.getAddress2().trim().equals("")) 
		{
			log.info("상세주소 에러-1");
			ra.addFlashAttribute("address_err_msg", "상세 주소를 입력하십시오");
		}
		
		if (memberDTO.getZipNum().equals("") &&
			memberDTO.getAddress1().equals("") &&
		    !memberDTO.getAddress2().trim().equals("")) 
		{
			log.info("상세주소 에러-2");
			ra.addFlashAttribute("address_err_msg", "주소 찾기 버튼으로 주소를 검색하십시오");
		}
		
		//////////////////////////////////////////////////////////////////////////
		
		if (result.hasErrors()) {
			
			log.error("폼점검 에러");
			result.getAllErrors().forEach(x->{ log.error(x); });
			
			ra.addFlashAttribute("org.springframework.validation.BindingResult.memberDTO", result);
			
			page = "redirect:/member/member_update";
						
		} else {
			
			log.info("폼점검 완료");
			log.info("MemberDTO : {}", memberDTO);
			
			// 이메일 중복 점검
			boolean emailCheck = memberService.isEmailOnUpdate(memberDTO.getId(), memberDTO.getEmail());
			boolean phoneCheck = memberService.isPhoneOnUpdate(memberDTO.getId(), memberDTO.getPhone());
			
			if (emailCheck == false) {
				
				log.info("이메일 중복");
				ra.addFlashAttribute("email_err_msg", "해당 이메일은 이미 사용하고 있습니다");
				
			} 
			
			if (phoneCheck == false) {
				
				log.info("연락처 중복");
				ra.addFlashAttribute("phone_err_msg", "해당 연락처는 이미 사용하고 있습니다");
				
			} 
			
			// 수정 회원 정보 점검 완료
			if (emailCheck == true && phoneCheck == true) {
			
				// DTO => VO
				// howto-1)
				// MemberVO memberVO = new MemberVO(memberDTO);
				// log.info("memberVO : {}", memberVO);
				
				// howto-2)
				MemberVO memberVO = (MemberVO)session.getAttribute("LOGIN_USER");
				
				// 패쓰워드 정보 변경시 변경 반영(수정)
				if (!pwd.equals("")) {
					memberVO.setPwd(pwd);
				}
				
				// 이메일 정보 변경시 변경 반영(수정)
				if (!memberVO.getEmail().equals(memberDTO.getEmail())) {
					memberVO.setEmail(memberDTO.getEmail());
				}
				
				// 연락처 정보 변경시 변경 반영(수정)
				if (!memberVO.getPhone().equals(memberDTO.getPhone())) {
					memberVO.setPhone(memberDTO.getPhone());
				}
				
				// 주소 정보 변경 반영(수정)
				memberVO.setZipNum(memberDTO.getZipNum());
				memberVO.setAddress1(memberDTO.getAddress1());
				memberVO.setAddress2(memberDTO.getAddress2());
				
				log.info("업데이트(수정) memberVO : {}", memberVO);
				
				String updateMsg = "";
				
				if (memberService.updateMember(memberVO) == true) {
					
					log.info("회원 정보 수정 성공");
					updateMsg = "회원 정보 수정에 성공하였습니다";
					
				} else {
					
					log.info("회원 정보 수정 실패");
					updateMsg = "회원 정보 수정에 실패하였습니다";
				}
				
				ra.addFlashAttribute("update_success_msg", updateMsg);
				
				// 기존 정보(session) 변경
				session.setAttribute("LOGIN_USER", memberVO);
				
			} // 수정 회원 정보 점검 완료 	
			
			page = "redirect:/member/member_update";
		}
		
		return page;
	}

	@RequestMapping("member_inactive")
	public String memberInactiveProc(HttpSession session, Model model) {
		
		// 회원 탈퇴 처리시 회원 정보 삭제가 아닌 회원 정보 활성화 필드(useyn)을 비활성화(n)로 설정
		// 회원 정보 = 회사 자산
		log.info("회원 탈퇴 처리");
		
		String errMsg = ""; // 에러 메시지
		String path = "";
		String id = ((MemberVO)session.getAttribute("LOGIN_USER")).getId();
		
		if (memberService.inactiveMemberRole(id) == true) {
			
			// 세션 종료(로그 아웃)
			session.invalidate();
			errMsg = "성공적으로 회원 탈퇴 처리를 하였습니다";
			path = "/"; // 홈페이지로 이동
			
		} else {
			
			// 회원 탈퇴 
			errMsg = "회원 탈퇴 처리에 실패하였습니다";
			path = "/member/member_info";
		}
		
		model.addAttribute("err_msg", errMsg);
		model.addAttribute("path", path);
		
		return "/error/error";
	}
	
	@GetMapping("checkInactiveMember")
	public String checkInActiveMember() {
		
		log.info("탈퇴한 회원 여부 점검");
		
		return "/member/check_inactive_member";
	}
	
	@PostMapping("check_inactive_member_proc")
	public String checkInactiveMemberProc(@ModelAttribute MemberVO memberVO, Model model) {
		
		log.info("탈퇴한 회원 여부 점검 처리 : {}", memberVO);
		
		String errMsg = "";
		String movePath = "";
		
		log.info("isMemberByRequired : " + memberService.isMemberByRequired(memberVO));
		
		// 기존 회원 정보 존재 여부
		// 기존 정보가 있음(탈퇴 회원 정보 존재)
		if (memberService.isMemberByRequired(memberVO) == true) {
		
			// 기존 회원 정보 재사용 (true) 설정
			// role : 'guest' => 'user'
			// useyn : 'n' => 'y'
			if (memberService.activeMemberRole(memberVO.getId(), "user", "y") == true) {
				
				log.info("기존(탈퇴 전) 회원 정보 갱신 성공");
				
				errMsg = "기존(탈퇴 전) 회원 정보로 갱신하였고, 로그인 페이지로 이동하겠습니다";
				movePath = "/login/login_form";
				
			} else {
				
				log.info("기존(탈퇴 전) 회원 정보 갱신 실패");
				
				errMsg = "기존(탈퇴 전) 회원 정보 갱신에 실패하였습니다";
				movePath = "/member/join_form?contractOK=true";
			}
			
		// 기존 정보가 없음(신규 회원)	
		} else {
			
			errMsg = "기존(탈퇴 전) 회원 정보가 존재하지 않습니다. 회원 가입 페이지로 이동하겠습니다";
			movePath = "/member/join_form?contractOK=true";
		}
		
		model.addAttribute("err_msg", errMsg);
		model.addAttribute("move_path", movePath);
		
		return "/error/error";
	} //
}
