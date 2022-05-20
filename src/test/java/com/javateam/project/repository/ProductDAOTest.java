package com.javateam.project.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/db-context.xml"})
@Slf4j
public class ProductDAOTest {

	@Autowired
	ProductDAO productDAO;
	
//	@Before
//	public void setUp() throws Exception {
//	}

	@Test
	public void test() {
		log.info("dao.listBestProduct unit test");
		
		// log.info("첫번째 상품 : " + productDAO.listBestProduct().get(0));
		assertNotEquals(productDAO.listBestProduct().get(0).getPrice2(), 5700);
		assertEquals(productDAO.listBestProduct().get(0).getPrice2(), 5500);
	}

}
