package com.javateam.project.repository;

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
public class ProductDAOKindTest {

	@Autowired
	ProductDAO productDAO;
	
	@Test
	public void test() {
		log.info("dao.listKindProduct unit test");
		
		assertEquals(productDAO.listKindProduct(5).size(), 1);
	}
	
	@Test
	public void test2() {
		log.info("dao.listKindProduct unit test2");
		
		assertEquals(productDAO.listKindProduct(5).get(0).getName(), "스니커즈");
	}


}
