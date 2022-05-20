package com.javateam.project.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
public class AdminOrderlistOrderTest {

	@Autowired
	OrderDAO orderDAO;
	
	@Transactional(readOnly=true)
	@Test
	public void test() {
	
		log.info("admin order listOrder unit test");
		
		// assertNotNull(orderDAO.listOrder(""));
		assertEquals(orderDAO.listOrder("").size(), 4);
	}	
}
