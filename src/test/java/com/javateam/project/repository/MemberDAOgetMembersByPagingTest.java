package com.javateam.project.repository;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.project.domain.MemberRoleVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/db-context.xml"})
@Slf4j
public class MemberDAOgetMembersByPagingTest {

	@Autowired
	SqlSession sqlSession;
	
	@Transactional(readOnly=true)
	@Test
	public void test() {
		log.info("getMembersByPaging unit test");
		
		Map<String, Integer> map = new HashMap<>();
		map.put("page", 2);
		map.put("limit", 10);
		
		List<MemberRoleVO> members = sqlSession.selectList("com.javateam.project.mapper.Member.getMembersByPaging", map);
		
		// assertEquals(10, members.size());
		// assertEquals(members.get(0).getId(), "abcd1234");
		
		// log.info("MemberRoleVO : " + members.get(0));
		log.info("출력 현황");
		
		for (MemberRoleVO m : members) {
			log.info("{}", m);
		} //
		
	}

}
