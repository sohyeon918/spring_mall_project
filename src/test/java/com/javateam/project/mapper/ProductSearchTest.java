package com.javateam.project.mapper;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.project.domain.ProductVO;
import com.javateam.project.domain.SearchProductVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/db-context.xml"})
@Slf4j
public class ProductSearchTest {

	@Autowired
	SqlSession sqlSession;
	
	SearchProductVO searchProductVO;
	
	@Before
	public void setup() {
		
		searchProductVO = new SearchProductVO();
		searchProductVO.setPage(1);
		searchProductVO.setLimit(5);
	}

	@Test
	public void test() {
		/*
		-- 상품 분류(O) + 검색어(O)
		WHERE kind = 1 AND name like '%힐%'
		 */
		log.info("Product Mapper.search unit test");
		
		searchProductVO.setSearchFld("kind");
		searchProductVO.setSearchFldVal("1");
		searchProductVO.setSearchWord("힐");
		
		List<ProductVO> products 
			= sqlSession.selectList("com.javateam.project.mapper.Product.searchProductsByPaging", 
						searchProductVO);
		
		for (ProductVO p : products) {
			log.info("상품 : " + p);
		} //
		
	}
	
	@Test
	public void test2() {
		/*
		-- 상품 분류(X) + 검색어(O)
		WHERE name like '%스니커즈%'
		 */
		log.info("Product Mapper.search unit test");
		
		// searchProductVO.setSearchFld(null);
		// searchProductVO.setSearchFldVal(null);
		searchProductVO.setSearchWord("스니커즈");
		
		List<ProductVO> products 
			= sqlSession.selectList("com.javateam.project.mapper.Product.searchProductsByPaging", 
						searchProductVO);
		
		for (ProductVO p : products) {
			log.info("상품2 : " + p);
		} //
	}
	
	@Test
	public void test3() {
		/*
		-- 상품 분류(O) + 검색어(X)
		WHERE kind = 1 
		 */
		log.info("Product Mapper.search unit test");
		
		searchProductVO.setSearchFld("kind");
		searchProductVO.setSearchFldVal(1); // Heels => 1
		// searchProductVO.setSearchWord(null);
		// searchProductVO.setSearchWord("");
		
		List<ProductVO> products 
			= sqlSession.selectList("com.javateam.project.mapper.Product.searchProductsByPaging", 
						searchProductVO);
		
		for (ProductVO p : products) {
			log.info("상품3 : " + p);
		} //
	}

}
