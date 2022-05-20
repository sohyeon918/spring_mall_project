package com.javateam.project.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.project.domain.MemberRoleVO;
import com.javateam.project.domain.SearchVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/db-context.xml"})
@Slf4j
public class SearchMembersByPagingTest {

	@Autowired
	SqlSession sqlSession;
	
	SearchVO searchVO;
	
	@Before
	public void setUp() {
		
		searchVO = new SearchVO();
		searchVO.setPage(1);
		searchVO.setLimit(10);
		searchVO.setIsLike("like");
		searchVO.setSearchFld("name");
		searchVO.setSearchWord("%이%");
	}
	
	@Transactional(readOnly=true)
	@Test
	public void test() {
		log.info("Member Mapper searchMembersByPaging unit test");
		
		// 첫 페이지
		List<MemberRoleVO> members 
			= sqlSession.selectList("com.javateam.project.mapper.Member.searchMembersByPaging", searchVO);
		
		// 주의사항) 인원 정보 데이터의 상황이 상대적으로 다를 수 있으므로 기대값(expected)는 개인 상황에 맞게 적용
		assertEquals(10, members.size());
	}

	@Transactional(readOnly=true)
	@Test
	public void test2() {
		log.info("Member Mapper searchMembersByPaging unit test2");
		
		// 두번째 페이지
		searchVO.setPage(2);
		
		List<MemberRoleVO> members 
			= sqlSession.selectList("com.javateam.project.mapper.Member.searchMembersByPaging", searchVO);
		
		// 주의사항) 인원 정보 데이터의 상황이 상대적으로 다를 수 있으므로 기대값(expected)는 개인 상황에 맞게 적용
		assertEquals(7, members.size());
	}
	
	@Transactional(readOnly=true)
	@Test
	public void test3() {
		log.info("Member Mapper searchMembersByPaging unit test3");
		
		// 아이디
		searchVO.setPage(1);
		searchVO.setIsLike("=");
		searchVO.setSearchFld("m.id");
		searchVO.setSearchWord("ezen_1014");
		
		List<MemberRoleVO> members 
			= sqlSession.selectList("com.javateam.project.mapper.Member.searchMembersByPaging", searchVO);
		
		// 주의사항) 인원 정보 데이터의 상황이 상대적으로 다를 수 있으므로 기대값(expected)는 개인 상황에 맞게 적용
		assertEquals(1, members.size());
	}
	
	@Transactional(readOnly=true)
	@Test
	public void test4() {
		log.info("Member Mapper searchMembersByPaging unit test4");
		
		// 기본 주소
		searchVO.setPage(1); 
		searchVO.setIsLike("like");
		searchVO.setSearchFld("address1");
		searchVO.setSearchWord("%화곡동%");
		
		List<MemberRoleVO> members 
			= sqlSession.selectList("com.javateam.project.mapper.Member.searchMembersByPaging", searchVO);
		
		// 주의사항) 인원 정보 데이터의 상황이 상대적으로 다를 수 있으므로 기대값(expected)는 개인 상황에 맞게 적용
		assertEquals(10, members.size());
	}
	
	@Transactional(readOnly=true)
	@Test
	public void test5() {
		log.info("Member Mapper searchMembersByPaging unit test4");
		
		// 상세 주소
		searchVO.setPage(1);
		searchVO.setIsLike("like");
		searchVO.setSearchFld("address2");
		searchVO.setSearchWord("%심당빌딩 404호%");
		
		List<MemberRoleVO> members 
			= sqlSession.selectList("com.javateam.project.mapper.Member.searchMembersByPaging", searchVO);
		
		// 주의사항) 인원 정보 데이터의 상황이 상대적으로 다를 수 있으므로 기대값(expected)는 개인 상황에 맞게 적용
		assertEquals(3, members.size());
	}
}
