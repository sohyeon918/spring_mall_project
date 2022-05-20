package com.javateam.project.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javateam.project.domain.ProductVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/db-context.xml"})
@Slf4j
public class ProductMapperTest {

	@Autowired
	SqlSession sqlSession;
	
//	@Before
//	public void setUp() throws Exception {
//	}

	@Test
	public void test() {
		log.info("mapper.listBestProduct unit test");
		
		List<ProductVO> list = sqlSession.selectList("com.javateam.project.mapper.Product.listBestProduct");
		
		assertNotEquals(list.get(0).getPrice2(), 5700);
		assertEquals(list.get(0).getPrice2(), 5500);
	}

}
