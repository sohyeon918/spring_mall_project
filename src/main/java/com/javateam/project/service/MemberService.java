package com.javateam.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.transaction.support.TransactionCallback;
// import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.javateam.project.domain.MemberRoleVO;
import com.javateam.project.domain.MemberVO;
import com.javateam.project.domain.RoleVO;
import com.javateam.project.domain.SearchVO;
import com.javateam.project.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	TransactionTemplate txTemplate;
	
	/**
	 * 회원 존재 여부 점검
	 * 
	 * @param id 회원 아이디
	 * @return 회원 존재 여부
	 */
	@Transactional(readOnly=true)
	public boolean isMember(String id) {
		
		log.info("MemberService.isMember");
		
		return txTemplate.execute(status -> { return memberDAO.isMember(id); });

//		return txTemplate.execute(new TransactionCallback<Boolean>() {
//
//			@Override
//			public Boolean doInTransaction(TransactionStatus status) {
//				return memberDAO.isMember(id);
//			}
//			
//		});
	}
	
	/**
	 * 이메일 존재 여부 점검
	 * 
	 * @param email 회원 이메일
	 * @return 이메일 존재 여부
	 */
	@Transactional(readOnly=true)
	public boolean isEmail(String email) {
		
		log.info("MemberService.isEmail");
		
		return txTemplate.execute(status -> { return memberDAO.isEmail(email); });
	}
	
	/**
	 * 연락처(휴대폰) 존재 여부 점검
	 * 
	 * @param phone 회원 연락처(휴대폰)
	 * @return 연락처(휴대폰) 존재 여부
	 */
	@Transactional(readOnly=true)
	public boolean isPhone(String phone) {
		
		log.info("MemberService.isPhone");
		
		return txTemplate.execute(status -> { return memberDAO.isPhone(phone); });
	}
	
	// @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	/*
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertMember(final MemberVO memberVO) {
		
		log.info("MemberService.insertMember");
		
		txTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				
				try {
					memberDAO.insertMember(memberVO);
					log.info("회원 정보 저장 성공");
				} catch (Exception e) {
					status.setRollbackOnly();
					log.error("MemberService.insertMember error : " + e.getMessage());
				} //
			} 
		});
	} //
	*/
	
//	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
//	public void insertMember(final MemberVO memberVO) {
//		
//		log.info("MemberService.insertMember");
//		
//		txTemplate.execute(status -> { memberDAO.insertMember(memberVO);  return null; });
//	} //
	
	/*
	// @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertMember(final MemberVO memberVO) {
		
		log.info("MemberService.insertMember");
		
		return txTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				
				boolean flag = false;
				
				try {
					memberDAO.insertMember(memberVO);
					log.info("회원 정보 저장 성공");
					flag = true;
				} catch (Exception e) {					
					status.setRollbackOnly();
					flag = false;
					log.error("MemberService.insertMember error : " + e.getMessage());
				} //
				
				return flag;
			} //
			
		});
	} //
	*/
	
	/**
	 * 개별 회원 정보 생성
	 * 
	 * @param memberVO 회원 정보 객체
	 * @param roleVO 회원 롤(계층) 객체
	 * @return 회원 정보 생성 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertMember(final MemberVO memberVO, final RoleVO roleVO) {
		
		log.info("MemberService.insertMember");
		
		return txTemplate.execute(status -> {
				
				boolean flag = false;
				
				try {
					memberDAO.insertMember(memberVO);
					log.info("회원 정보 저장 성공");
					
					memberDAO.insertRole(roleVO);
					log.info("회원 정보 롤(role) 저장 성공");
					
					flag = true;
				} catch (Exception e) {					
					status.setRollbackOnly();
					flag = false;
					log.error("MemberService.insertMember error : " + e.getMessage());
				} //
				
				return flag;
		});
	} //
	
	
	@Transactional(readOnly=true)
	public String checkLogin(String id, String pwd) {
		
		log.info("MemberService.checkLogin");
		String msg = "";
		// case-1 : 아이디 없음
		// case-2 : 아이디 존재하지만 패쓰워드 불일치
		// case-3 : 정상 로그인(아이디/패쓰워드 모두 일치)
		
		if (memberDAO.isMember(id) == true) { // 회원 아이디 존재
			
			if (memberDAO.isMemberByIdPwd(id, pwd) == true) { // case-3
				
				msg = "아이디/패쓰워드가 모두 일치합니다.";
				
			} else { // case-2
				
				msg = "패쓰워드가 일치하지 않습니다.";
			}
			
		} else { // 회원 아이디 없음 : case-1
			
			msg = "회원 아이디가 존재하지 않습니다.";
		}
		
		return msg;
	} //
	
	/**
	 * 개별 회원 정보 조회
	 * 
	 * @param id 회원 아이디
	 * @return 회원 정보 객체
	 */
	@Transactional(readOnly=true)
	public MemberVO getMember(String id) {
		
		log.info("MemberService.getMember");
		
		return txTemplate.execute(status -> { return memberDAO.getMember(id); });
	} //
	
	/**
	 * 개별 회원 정보 수정(갱신)
	 * 
	 * @param memberVO 회원 정보 객체
	 * @return 회원 정보 생성 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateMember(final MemberVO memberVO) {
		
		log.info("MemberService.updateMember");
		
		return txTemplate.execute(status -> {
				
				boolean flag = false;
				
				try {
					memberDAO.updateMember(memberVO);
					log.info("회원 정보 수정(갱신) 성공");
					
					flag = true;
				} catch (Exception e) {					
					status.setRollbackOnly();
					flag = false;
					log.error("MemberService.updateMember error : " + e.getMessage());
				} //
				
				return flag;
		});
	} //
	
	/**
	 * 이메일 사용 가능 여부 점검
	 * 
	 * @param id 회원 아이디
	 * @param email 회원 이메일
	 * @return 이메일 사용 가능 여부
	 */
	@Transactional(readOnly=true)
	public boolean isEmailOnUpdate(String id, String email) {
		
		log.info("MemberService.isEmailOnUpdate");
		
		return txTemplate.execute(status -> { return !memberDAO.isEmailOnUpdate(id, email); });
	}
	
	/**
	 * 연락처 사용 가능 여부 점검
	 * 
	 * @param id 회원 아이디
	 * @param phone 회원 연락처
	 * @return 연락처 사용 가능 여부
	 */
	@Transactional(readOnly=true)
	public boolean isPhoneOnUpdate(String id, String phone) {
		
		log.info("MemberService.isPhoneOnUpdate");
		
		return txTemplate.execute(status -> { return !memberDAO.isPhoneOnUpdate(id, phone); });
	}
	
	/**
	 * 회원 탈퇴 처리
	 * 
	 * @param id 회원 아이디
	 * @return 회원 탈퇴 처리 성공 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean inactiveMemberRole(final String id) {
		
		log.info("MemberService.inactiveMemberRole");
		
		return txTemplate.execute(status -> {
				
				boolean flag = false;
				
				try {
					
					if (memberDAO.inactiveMember(id) == true) {
						
						log.info("회원 탈퇴(비활성화) 처리 성공");
						
						if (memberDAO.inactiveRole(id) == true) {
							log.info("회원 정보 롤(role) 비활성화(guest) 성공");
						} else {
							throw new Exception("회원 탈퇴(비활성화) 처리 실패");
						}
						
					} else {
						throw new Exception("회원 탈퇴(비활성화) 처리 실패");
					}
					
					flag = true;
					
				} catch (Exception e) {					
					status.setRollbackOnly();
					flag = false;
					log.error("MemberService.inactiveMemberRole error : " + e.getMessage());
				} //
				
				return flag;
		});
	} //
	
	/**
	 * 회원 활성화 여부 점검(활동/탈퇴 회원 점검)
	 * 
	 * @param id 회원 아이디
	 * @return 활동 회원(회원 탈퇴) 여부  ex) true : 활동 회원, false : 탈퇴 회원
	 */
	@Transactional(readOnly=true)
	public boolean isActiveMember(final String id) {
		
		log.info("MemberService.isActiveMember");
		
		return txTemplate.execute(status -> { return memberDAO.isActiveMember(id); });
	}
	
	/**
	 * 회원 활성화/비활성화 갱신
	 * 
	 * @param id 회원 아이디
	 * @param role 회원 롤(role) ex) 회원(user), 관리자(admin), 탈퇴 회원(guest)
	 * @param useyn 회원 활성화 여부 ex) 'y' : 활동 회원, 'n' : 탈퇴 회원
	 * @return 회원 상태((비)활성화) 갱신
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean activeMemberRole(final String id, final String role, final String useyn) {
		
		log.info("MemberService.activeMemberRole");
		
		return txTemplate.execute(status -> {
				
				boolean flag = false;
				
				try {
					memberDAO.activeMember(id, useyn);
					log.info("회원 상태((비)활성화) 변경 성공");
					
					memberDAO.activeRole(id, role);
					log.info("회원 정보 롤(role) 성공");
					
					flag = true;
				} catch (Exception e) {					
					status.setRollbackOnly();
					flag = false;
					log.error("MemberService.activeMemberRole error : " + e.getMessage());
				} //
				
				return flag;
		});
	} //
	
	/**
	 * (필수 항목으로) 회원 정보 유무 조회 ex) 탈퇴한 회원 여부 점검
	 * 
	 * @param memberVO 회원 정보 객체  ex) 아이디, 패쓰워드, 이메일, 연락처
	 * @return 회원 정보 존재 여부
	 */
	@Transactional(readOnly=true)
	public boolean isMemberByRequired(final MemberVO memberVO) {
		
		log.info("MemberService.isMemberByRequired");
		
		return txTemplate.execute(status -> { return memberDAO.isMemberByRequired(memberVO); });
	}
	
	/**
	 * 개별 회원 롤(role) 정보 조회
	 * 
	 * @param id 회원 아이디
	 * @return 회원 롤(role)
	 */
	@Transactional(readOnly=true)
	public String getRole(final String id) {
		
		log.info("MemberService.getRole");
		
		return txTemplate.execute(status -> { return memberDAO.getRole(id); });
	}
	
	/**
	 * 전체 회원 조회 (페이징 적용)
	 * 
	 * @param page 현재 페이지
	 * @param limit 페이지당 자료(게시글) 수
	 * @return 인원 정보 리스트
	 */
	@Transactional(readOnly=true)
	public List<MemberRoleVO> getMembersByPaging(final int page, final int limit) {
		
		log.info("MemberService.getMembersByPaging");
		
		return txTemplate.execute(status -> { return memberDAO.getMembersByPaging(page, limit); });
	}
	
	/**
	 * 총 회원 수
	 * 
	 * @return 총 회원 수
	 */
	@Transactional(readOnly=true)
	public int getTotalMembersCount() {
		
		log.info("MemberService.getTotalMembersCount");
		
		return txTemplate.execute(status -> { return memberDAO.getTotalMembersCount(); });
	}
	
	/**
	 * 검색(페이징 적용)
	 * 
	 * @param searchVO 검색 VO
	 * @return 검색 결과(회원 정보들)
	 */
	@Transactional(readOnly=true)
	public List<MemberRoleVO> searchMembersByPaging(SearchVO searchVO) {
		
		log.info("MemberService.searchMembersByPaging");
		
		return txTemplate.execute(status -> { return memberDAO.searchMembersByPaging(searchVO); });
	}
	
	/**
	 * 검색 (페이징) 총 인원수
	 * 
	 * @param searchVO 검색 VO
	 * @return 총 검색 결과수
	 */
	@Transactional(readOnly=true)
	public int getCountSearchMembersByPaging(SearchVO searchVO) {
		
		log.info("MemberService.getCountSearchMembersByPaging : " + searchVO);
		
		return txTemplate.execute(status -> { return memberDAO.getCountSearchMembersByPaging(searchVO); });
	}
	
	/**
	 * 관리자 모드 : 개별 회원 정보 수정 ex) 이메일/연락처/우편번호/기본주소/상세주소
	 * 
	 * @param id 회원 아이디
	 * @param fld 수정할 필드
	 * @param val 수정할 필드값
	 * @return 회원 개별 필드 수정 성공 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean upateMemberByAdmin(final String id, final String fld, final String val) {
		
		log.info("MemberService.upateMemberByAdmin");
		
		return txTemplate.execute(status -> {
				
				boolean flag = false;
				
				try {
					memberDAO.updateMemberByAdmin(id, fld, val);
					log.info("회원 정보 개별 필드 수정 성공");
					
					flag = true;
				} catch (Exception e) {					
					status.setRollbackOnly();
					flag = false;
					log.error("MemberService.upateMemberByAdmin error : " + e.getMessage());
				} //
				
				return flag;
		});
	} //
	
}
