package com.javateam.project.service;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.project.domain.CartVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/db-context.xml"})
@Slf4j
public class CartServiceCheckCartTest {
	
	@Inject
	CartService cartService;
	
	List<CartVO> cartList; 
	CartVO cartVO;

	@Before
	public void setUp() {
		
		cartList = new ArrayList<>();
		cartVO = new CartVO();
		
		cartVO.setCseq(1);
		cartVO.setId("abcd1234");
		cartVO.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO.setPname("슬리퍼 412123024 (6.5cm/3colors)");
		cartVO.setPseq(563);
		cartVO.setQuantity(10);
		cartVO.setResult(1);
		
		cartList.add(cartVO);
		
		////////////////////////////////////////
		
		CartVO cartVO2 = new CartVO();
		
		cartVO2.setCseq(2);
		cartVO2.setId("abcd1234");
		cartVO2.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO2.setPname("펌프스 412211005 (6.5cm/2colors)");
		cartVO2.setPseq(520);
		cartVO2.setQuantity(5);
		cartVO2.setResult(1);
		
		cartList.add(cartVO2);
	}
	
	@Test
	public void test() {
		
		log.info("CartService.checkCart equals Test");
		
		CartVO cartVO = new CartVO();
		
		cartVO.setCseq(1);
		cartVO.setId("abcd1234");
		cartVO.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO.setPname("슬리퍼 412123024 (6.5cm/3colors)");
		cartVO.setPseq(563);
		cartVO.setQuantity(10);
		cartVO.setResult(1);
		
		CartVO cartVO2 = new CartVO();
		
		cartVO2.setCseq(2);
		cartVO2.setId("abcd1234");
		cartVO2.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO2.setPname("펌프스 412211005 (6.5cm/2colors)");
		cartVO2.setPseq(520);
		cartVO2.setQuantity(5);
		cartVO2.setResult(1);
		
		CartVO cartVO3 = new CartVO();
		
		cartVO3.setCseq(3);
		cartVO3.setId("abcd1234");
		cartVO3.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO3.setPname("펌프스 412211005 (6.5cm/2colors)");
		cartVO3.setPseq(520);
		cartVO3.setQuantity(2);
		cartVO3.setResult(1);

		
		// assertTrue(cartVO.equals(cartVO3));
		assertTrue(cartVO2.equals(cartVO3));
		
	} //
}
