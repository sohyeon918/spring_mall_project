package com.javateam.project.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/db-context.xml"})
@Slf4j
public class MemberServiceIsMemberTest {

	@Autowired
	MemberService memberService;
	
	@Test
	public void test() {
		log.info("MemberService isMember unit test");
		
		assertTrue(memberService.isMember("java1234"));
	} //
	
	@Test
	public void test2() {
		log.info("MemberService isMember unit test2");
		
		assertFalse(memberService.isMember("servlet1234"));
	} //

}
