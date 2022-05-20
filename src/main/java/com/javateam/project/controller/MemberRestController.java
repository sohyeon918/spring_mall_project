package com.javateam.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javateam.project.domain.MemberVO;
import com.javateam.project.service.MemberService;

@RestController
@RequestMapping("member")
public class MemberRestController {
	
	private static final Logger log 
		= LoggerFactory.getLogger(MemberRestController.class);
	
	@Autowired
	MemberService memberService;
	
//	@GetMapping(value="id_check", produces="text/plain; charset=UTF-8")
//	public String idCheck(@RequestParam("id") String id) {
//		
//		log.info("아이디 중복 점검 Rest Service id : " + id);
//		String result = ""; // "중복 점검 전송 성공";
//		
//		result = memberService.isMember(id) == true ? "회원 존재" : "회원이 존재하지 않음"; 
//		
//		return result;
//	} //
	
	@GetMapping(value="id_check", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> idCheck(@RequestParam("id") String id) {
		
		log.info("아이디 중복 점검 Rest Service id : " + id);
		
		try {
			// String result = memberService.isMember(id) == true ? "회원 존재" : "회원이 존재하지 않음";
			
			// 회원이 존재할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.isMember(id) == true) {
				return new ResponseEntity<String>("회원 존재", HttpStatus.OK);
			// 회원이 존재하지 않을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				// return new ResponseEntity<String>("회원이 존재하지 않음", HttpStatus.NO_CONTENT);				
				return new ResponseEntity<String>("회원이 존재하지 않음", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("idCheck REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	@GetMapping(value="email_check", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> emailCheck(@RequestParam("email") String email) {
		
		log.info("이메일 중복 점검 Rest Service email : " + email);
		
		try {
			
			// 이메일이 존재할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.isEmail(email) == true) {
				return new ResponseEntity<String>("중복 이메일 존재", HttpStatus.OK);
			// 이메일이 존재하지 않을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				// return new ResponseEntity<String>("이메일이 존재하지 않음", HttpStatus.NO_CONTENT);				
				return new ResponseEntity<String>("사용 가능한 이메일", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("emailCheck REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	@GetMapping(value="phone_check", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> phoneCheck(@RequestParam("phone") String phone) {
		
		log.info("연락처(휴대폰) 중복 점검 Rest Service phone : " + phone);
		
		try {
			
			// 연락처(휴대폰)가 존재할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.isPhone(phone) == true) {
				return new ResponseEntity<String>("중복 연락처(휴대폰) 존재", HttpStatus.OK);
			// 연락처(휴대폰)가 존재하지 않을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				// return new ResponseEntity<String>("연락처(휴대폰)이 존재하지 않음", HttpStatus.NO_CONTENT);				
				return new ResponseEntity<String>("사용 가능한 연락처(휴대폰)", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("phoneCheck REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	
	@GetMapping(value="update_role", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> updateRole(@RequestParam("id") String id,
											 @RequestParam("role") String role) 
	{
		log.info("개별 회원 롤(role) 수정 : id={}, role={}", id, role);
		
		try {
			
			// 정상적으로 롤 정보가 수정되었을 때 (200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.activeMemberRole(id, role, "y") == true) {
				return new ResponseEntity<String>("회원 롤 정보가 정상적으로 수정되었음", HttpStatus.OK);
			// 롤 정보가 수정 실패 (204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 404(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				// return new ResponseEntity<String>("회원 롤 정보 수정에 실패하였음", HttpStatus.NO_CONTENT);				
				return new ResponseEntity<String>("회원 롤 정보 수정에 실패하였음", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("updateRole REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	@GetMapping(value="member_view", produces="application/json; charset=UTF-8")
	public ResponseEntity<MemberVO> memberView(@RequestParam("id") String id) {
		
		log.info("개별 회원 정보 조회 Rest Service, id : " + id);
		MemberVO memberVO = null;
		
		try {
			memberVO = memberService.getMember(id);
			
			// 회원이 존재할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberVO != null) {
				
				return new ResponseEntity<MemberVO>(memberVO, HttpStatus.OK);
				
			// 회원이 존재하지 않을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				// return new ResponseEntity<String>("회원이 존재하지 않음", HttpStatus.NO_CONTENT);				
				return new ResponseEntity<MemberVO>(memberVO, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("idCheck REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<MemberVO>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	@GetMapping(value="email_check_update", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> emailCheckOnUpdate(@RequestParam("id") String id,
													 @RequestParam("email") String email) {
		
		log.info("이메일 중복 점검 (수정) Rest Service id={}, email={} : ", id, email);
		
		try {
			
			// 이메일이 존재할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.isEmailOnUpdate(id, email) == true) {
				return new ResponseEntity<String>("사용 가능한 이메일", HttpStatus.OK);
			// 이메일이 존재하지 않을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				// return new ResponseEntity<String>("이메일이 존재하지 않음", HttpStatus.NO_CONTENT);				
				return new ResponseEntity<String>("중복 이메일 존재", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("emailCheckOnUpdate REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	@GetMapping(value="email_update", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> emailUpdate(@RequestParam("id") String id,
											  @RequestParam("email") String email) {
		
		log.info("이메일 수정 Rest Service id={}, email={} : ", id, email);
		
		try {
			
			// 이메일 수정 성공할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.upateMemberByAdmin(id, "email", email) == true) {
				return new ResponseEntity<String>("회원 개별 이메일  정보가 정상적으로 수정되었음", HttpStatus.OK);
			// 이메일 수정 실패했을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				// return new ResponseEntity<String>("이메일이 존재하지 않음", HttpStatus.NO_CONTENT);				
				return new ResponseEntity<String>("회원 개별 이메일  정보 수정에 실패하였음", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("emailUpdate REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	@GetMapping(value="phone_check_update", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> phoneCheckOnUpdate(@RequestParam("id") String id,
													 @RequestParam("phone") String phone) {
		
		log.info("연락처 중복 점검 (수정) Rest Service id={}, phone={} : ", id, phone);
		
		try {
			
			// 연락처가 존재할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.isPhoneOnUpdate(id, phone) == true) {
				return new ResponseEntity<String>("사용 가능한 연락처", HttpStatus.OK);
			// 연락처이 존재하지 않을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				// return new ResponseEntity<String>("연락처가 존재하지 않음", HttpStatus.NO_CONTENT);				
				return new ResponseEntity<String>("중복 연락처 존재", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("phoneCheckOnUpdate REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	@GetMapping(value="phone_update", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> phoneUpdate(@RequestParam("id") String id,
											  @RequestParam("phone") String phone) {
		
		log.info("연락처 수정 Rest Service id={}, phone={} : ", id, phone);
		
		try {
			
			// 연락처 수정 성공할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.upateMemberByAdmin(id, "phone", phone) == true) {
				return new ResponseEntity<String>("회원 개별 연락처  정보가 정상적으로 수정되었음", HttpStatus.OK);
			// 이메일 수정 실패했을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				// return new ResponseEntity<String>("연락처가 존재하지 않음", HttpStatus.NO_CONTENT);				
				return new ResponseEntity<String>("회원 개별 연락처  정보 수정에 실패하였음", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("phoneUpdate REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	@GetMapping(value="zip_address_update", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> zipAddress1Update(@RequestParam("id") String id,
												    @RequestParam("zipNum") String zipNum,
												    @RequestParam("address1") String address1,
												    @RequestParam("address2") String address2) 
	{
		log.info("우편번호/기본주소 수정 Rest Service id={}, zipNum={}, address1={}, address2={}: ", 
				  id, zipNum, address1, address2);
		
		try {
			
			// 우편번호/기본주소 수정 성공할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.upateMemberByAdmin(id, "zip_num", zipNum) == true && 
				memberService.upateMemberByAdmin(id, "address1", address1) == true && 
				memberService.upateMemberByAdmin(id, "address2", address2) == true) 
			{
				return new ResponseEntity<String>("회원 개별 우편번호/기본주소 정보가 정상적으로 수정되었음", HttpStatus.OK);
			// 우편번호/기본주소 수정 실패했을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				return new ResponseEntity<String>("회원 개별 우편번호/기본주소 정보 수정에 실패하였음", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("zipAddress1Update REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	@GetMapping(value="address2_update", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> address2Update(@RequestParam("id") String id,
												 @RequestParam("address2") String address2) 
	{
		log.info("상세주소 수정 Rest Service id={}, address2={} : ", id, address2);
		
		try {
			
			// 상세주소 수정 성공할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.upateMemberByAdmin(id, "address2", address2) == true) 
			{
				return new ResponseEntity<String>("회원 개별 상세주소 정보가 정상적으로 수정되었음", HttpStatus.OK);
			// 상세주소 수정 실패했을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				return new ResponseEntity<String>("회원 개별 상세주소 정보 수정에 실패하였음", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("address2Update REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
	@GetMapping(value="member_inactive_admin", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> memberInactiveByAdmin(@RequestParam("id") String id) 
	{
		log.info("개별 회원정보 비활성화(삭제) Rest Service id={}", id);
		
		try {
			
			// 개별 회원정보 비활성화(삭제) 성공할 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (memberService.inactiveMemberRole(id) == true) 
			{
				return new ResponseEntity<String>("개별 회원정보가 정상적으로 비활성화(삭제)되었음", HttpStatus.OK);
			// 개별 회원정보 비활성화(삭제) 실패했을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				// 204(콘텐츠 없음): 서버가 요청을 성공적으로 처리했지만 콘텐츠를 제공하지 않는다.
				return new ResponseEntity<String>("개별 회원정보 비활성화(삭제)에 실패하였음", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("memberInactiveByAdmin REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	} //
	
}