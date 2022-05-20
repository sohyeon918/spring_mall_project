package com.javateam.project.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/db-context.xml"})
@Slf4j
public class MemberDAOInactiveMemberTest {

	@Autowired
	MemberDAO dao;
	
	@Transactional(readOnly=true)
	@Test
	public void test() {
		log.info("MemberDAO.inactiveMember unit test");
		
		// 존재하지 않는 회원으로 삭제 테스트
		// assertTrue(dao.inactiveMember("abcdabcdabcd1234"));
		assertFalse(dao.inactiveMember("abcdabcdabcd1234"));
	}

}
