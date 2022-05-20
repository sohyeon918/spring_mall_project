package com.javateam.project.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.project.domain.MemberRoleVO;
import com.javateam.project.domain.MemberVO;
import com.javateam.project.domain.RoleVO;
import com.javateam.project.domain.SearchVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	/**
	 * 회원 존재 여부 점검
	 * 
	 * @param id 회원 아이디
	 * @return 회원 존재 여부
	 */
	public boolean isMember(String id) {
		
		log.info("MemberDAO.isMember");
		
		return (int)sqlSession.selectOne("com.javateam.project.mapper.Member.isMember", 
							id) == 1 ? true : false;
		
	} //

	/**
	 * 이메일 존재 여부 점검
	 * 
	 * @param email 이메일
	 * @return 이메일 존재 여부
	 */
	public boolean isEmail(String email) {

		log.info("MemberDAO.isEmail");
		return (int)sqlSession.selectOne("com.javateam.project.mapper.Member.isEmail", 
				email) == 1 ? true : false;
	} //
	
	/**
	 * 연락처(휴대폰) 존재 여부 점검
	 * 
	 * @param email 연락처(휴대폰)
	 * @return 연락처(휴대폰) 존재 여부
	 */
	public boolean isPhone(String phone) {

		log.info("MemberDAO.isPhone");
		return (int)sqlSession.selectOne("com.javateam.project.mapper.Member.isPhone", 
				phone) == 1 ? true : false;
	} //
	
	/**
	 * 회원 정보 생성(삽입)
	 * 
	 * @param memberVO 회원 정보 객체
	 */
	public void insertMember(MemberVO memberVO) {
		
		log.info("MemberDAO.insertMember");
		sqlSession.insert("com.javateam.project.mapper.Member.insertMember", memberVO);
	}
	
	/**
	 * 회원 정보 롤(role) 생성(삽입)
	 *  
	 * @param roleVO 롤 정보 객체
	 */
	public void insertRole(RoleVO roleVO) {
		
		log.info("MemberDAO.insertRole");
		sqlSession.insert("com.javateam.project.mapper.Member.insertRole", roleVO);
	}

	/**
	 * 회원 아이디/패쓰워드 일치 여부 점검 : ex) 로그인
	 * 
	 * @param id 회원 아이디
	 * @param pwd 회원 패쓰워드
	 * @return 아이디/패쓰워드 일치 회원 여부
	 */
	public boolean isMemberByIdPwd(String id, String pwd) {
		
		log.info("MemberDAO.isMemberByIdPwd");
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		
		// log.info("result : {}", (int)sqlSession.selectOne("com.javateam.project.mapper.Member.isMemberByIdPwd", member) == 1 ? true : false);
		
		return (int)sqlSession.selectOne("com.javateam.project.mapper.Member.isMemberByIdPwd", member)
				== 1 ? true : false;
	}
	
	/**
	 * 개별 회원 정보 조회
	 * 
	 * @param id 회원 아이디
	 * @return 회원 정보 객체
	 */
	public MemberVO getMember(String id) {
		
		log.info("MemberDAO.getMember");
		
		return sqlSession.selectOne("com.javateam.project.mapper.Member.getMember", id);
	}
	
	/**
	 * 개별 회원 정보 수정(갱신)
	 * 
	 * @param memberVO 회원 정보 객체
	 */
	public void updateMember(MemberVO memberVO) {
		
		log.info("MemberDAO.updateMember");
		
		sqlSession.update("com.javateam.project.mapper.Member.updateMember", memberVO);
	}
	
	/**
	 * 이메일 존재 여부 점검 : ex) 이메일 중복 점검(회원 정보 수정)
	 *  
	 * @param id 회원 아이디
	 * @param email 회원 이메일
	 * @return 이메일 존재 여부
	 */
	public boolean isEmailOnUpdate(String id, String email) {
		
		log.info("MemberDAO.isEmailOnUpdate");
		
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("email", email);
		
		return (int)sqlSession.selectOne("com.javateam.project.mapper.Member.isEmailOnUpdate", map) 
				== 1 ? true : false;
	}
	
	/**
	 * 연락처 존재 여부 점검 : ex) 연락처 중복 점검(회원 정보 수정)
	 *  
	 * @param id 회원 아이디
	 * @param phone 회원 연락처
	 * @return 연락처 존재 여부
	 */
	public boolean isPhoneOnUpdate(String id, String phone) {
		
		log.info("MemberDAO.isPhoneOnUpdate");
		
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("phone", phone);
		
		return (int)sqlSession.selectOne("com.javateam.project.mapper.Member.isPhoneOnUpdate", map) 
				== 1 ? true : false;
	}
	
	/**
	 * 회원 탈퇴 처리 : 회원 활성화 필드(useyn) 비활성('n')
	 * 
	 * @param id 회원 아이디
	 * @return 회원정보 수정 성공 여부
	 */
	public boolean inactiveMember(String id) {
		
		log.info("MemberDAO.inactiveMember");
		
		return sqlSession.update("com.javateam.project.mapper.Member.inactiveMember", id) >= 1 ? true : false;
	}
	
	/**
	 * 회원 탈퇴 처리 : 회원 롤 정보(role) 게스트(guest) 처리
	 * 
	 * @param id 회원 아이디
	 * @return 회원정보 롤(role) 수정 성공 여부
	 */
	public boolean inactiveRole(String id) {
		
		log.info("MemberDAO.inactiveRole");
		
		return sqlSession.update("com.javateam.project.mapper.Member.inactiveRole", id) >= 1 ? true : false;
	}
	
	/**
	 * 회원 활성화 여부 점검(활동/탈퇴 회원 점검)
	 * 
	 * @param id 회원 아이디
	 * @return 활동 회원(회원 탈퇴) 여부  ex) true : 활동 회원, false : 탈퇴 회원
	 */
	public boolean isActiveMember(String id) {
		
		log.info("MemberDAO.isActiveMember");
		
		return sqlSession.selectOne("com.javateam.project.mapper.Member.isActiveMember", id).equals("y") ? true : false;
	}
	
	/**
	 * 회원 활성/비활성(탈퇴) 갱신 : ex) 탈퇴 회원 갱신 처리(useyn 비/활성화(n/y))
	 * 
	 * @param id 회원 아이디
	 * @param useyn 회원 활성화 여부 ex) 'y' : 활동 회원, 'n' : 탈퇴 회원
	 */
	public void activeMember(String id, String useyn) {
		
		log.info("MemberDAO.activeMember");
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("useyn", useyn);
		
		sqlSession.update("com.javateam.project.mapper.Member.activeMember", map);
	}
	
	/**
	 * 회원 롤(role) 갱신(조정) : ex) 탈퇴 회원 갱신 처리(role=user), 관리자 변경(role=admin)
	 * 
	 * @param id 회원 아이디
	 * @param role 회원 롤(role) ex) 회원(user), 관리자(admin), 탈퇴 회원(guest)
	 */
	public void activeRole(String id, String role) {
		
		log.info("MemberDAO.activeRole");
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("role", role);
		
		sqlSession.update("com.javateam.project.mapper.Member.activeRole", map);
	}
	
	/**
	 * (필수 항목으로) 회원 정보 유무 조회 ex) 탈퇴한 회원 여부 점검
	 * 
	 * @param memberVO 회원 정보 객체  ex) 아이디, 패쓰워드, 이메일, 연락처
	 * @return 회원 정보 존재 여부
	 */
	public boolean isMemberByRequired(MemberVO memberVO) {
		
		log.info("MemberDAO.isMemberByRequired");
		
		return (int)sqlSession.selectOne("com.javateam.project.mapper.Member.isMemberByRequired", memberVO)
				 == 1 ? true : false;
	}
	
	/**
	 * 개별 회원 롤(role) 정보 조회
	 * 
	 * @param id 회원 아이디
	 * @return 회원 롤(role)
	 */
	public String getRole(String id) {
		
		log.info("MemberDAO.getRole");
		
		return sqlSession.selectOne("com.javateam.project.mapper.Member.getRole", id);
	}
	
	/**
	 * 전체 회원 조회 (페이징 적용)
	 * 
	 * @param page 현재 페이지
	 * @param limit 페이지당 자료(게시글) 수
	 * @return 인원 정보 리스트
	 */
	public List<MemberRoleVO> getMembersByPaging(int page, int limit) {
		
		log.info("MembeDAO.getMembersByPaging");
		
		Map<String, Integer> map = new HashMap<>();
		map.put("page", page);
		map.put("limit", limit);
		
		return sqlSession.selectList("com.javateam.project.mapper.Member.getMembersByPaging", map);
	}
	
	/**
	 * 총 회원 수
	 * 
	 * @return 총 회원 수
	 */
	public int getTotalMembersCount() {
		
		log.info("MemberDAO.getTotalMembersCount");
		
		return sqlSession.selectOne("com.javateam.project.mapper.Member.getTotalMembersCount");
	}
	
	/**
	 * 검색(페이징 적용)
	 * 
	 * @param searchVO 검색 VO
	 * @return 검색 결과(회원 정보들)
	 */
	public List<MemberRoleVO> searchMembersByPaging(SearchVO searchVO) {
		
		log.info("MemberDAO.searchMembersByPaging");
		
		// 검색 키워드(구분) 
		// 1) 아이디
		if (searchVO.getSearchFld().equals("id")) {
			
			searchVO.setIsLike("=");
			searchVO.setSearchFld("m.id");
			
		} else { // 2) 이름/기본주소/상세주소 검색
			
			searchVO.setIsLike("like");
			searchVO.setSearchWord("%"+searchVO.getSearchWord()+"%");
		}
		
		return sqlSession.selectList("com.javateam.project.mapper.Member.searchMembersByPaging", searchVO);
	}
	
	
	/**
	 * 검색 (페이징) 총 인원수
	 * 
	 * @param searchVO 검색 VO
	 * @return 총 검색 결과수
	 */
	public int getCountSearchMembersByPaging(SearchVO searchVO) {
		
		log.info("MemberDAO.getCountSearchMembersByPaging : " + searchVO);
		
		// 검색 키워드(구분) 
		// 1) 아이디
		if (searchVO.getSearchFld().equals("id")) {
			
			searchVO.setIsLike("=");
			searchVO.setSearchFld("m.id");
			
		} else { // 2) 이름/기본주소/상세주소 검색
			
			searchVO.setIsLike("like");
			searchVO.setSearchWord("%"+searchVO.getSearchWord()+"%");
		}
		
		return (int)sqlSession.selectOne("com.javateam.project.mapper.Member.getCountSearchMembersByPaging", searchVO);
	}
	
	/**
	 * 관리자 모드 : 개별 회원 정보 수정 ex) 이메일/연락처/우편번호/기본주소/상세주소
	 * 
	 * @param id 회원 아이디
	 * @param fld 수정할 필드
	 * @param val 수정할 필드값
	 */
	public void updateMemberByAdmin(String id, String fld, String val) {
		
		log.info("MemberDAO.updateMemberByAdmin : id={}, fld={}, val={}", id, fld, val );
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("fld", fld);
		map.put("val", val);		
		sqlSession.update("com.javateam.project.mapper.Member.updateMemberByAdmin", map);
	} //
}