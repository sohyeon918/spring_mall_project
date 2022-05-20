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
public class CartServiceCheckCartTest2 {
	
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
		
		cartVO = new CartVO();
		
		cartVO.setCseq(2);
		cartVO.setId("abcd1234");
		cartVO.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO.setPname("펌프스 412211005 (6.5cm/2colors)");
		cartVO.setPseq(520);
		cartVO.setQuantity(5);
		cartVO.setResult(1);
		
		cartList.add(cartVO);
	}
	
	@Test
	public void test() {
		
		log.info("CartService.checkCart Test");
		
		// 상품 코드 520 중복 주문 => 기대 주문 수량 : 7
		cartVO = new CartVO();
		
		cartVO.setCseq(3);
		cartVO.setId("abcd1234");
		cartVO.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO.setPname("펌프스 412211005 (6.5cm/2colors)");
		cartVO.setPseq(520);
		cartVO.setQuantity(2);
		cartVO.setResult(1);
		
		// 기존 카트 리스트에 중복 상품 여부 점검
		// assertTrue(cartService.checkCart(cartList, cartVO)); 
		// assertFalse(cartService.checkCart(cartList, cartVO));
		
		if (cartService.checkCart(cartList, cartVO) == false) {
			cartList.add(cartVO);
		}
		
		// 상품 코드 520 중복 주문-2 => 기대 주문 수량 : 8
		cartVO = new CartVO();
		
		cartVO.setCseq(4);
		cartVO.setId("abcd1234");
		cartVO.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO.setPname("펌프스 412211005 (6.5cm/2colors)");
		cartVO.setPseq(520);
		cartVO.setQuantity(1);
		cartVO.setResult(1);
		
		if (cartService.checkCart(cartList, cartVO) == false) {
			cartList.add(cartVO);
		}
		
		// 상품 코드 563 중복 주문 => 기대 주문 수량 : 15
		cartVO = new CartVO();
		
		cartVO.setCseq(5);
		cartVO.setId("abcd1234");
		cartVO.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO.setPname("슬리퍼 412123024 (6.5cm/3colors)");
		cartVO.setPseq(563);
		cartVO.setQuantity(5);
		cartVO.setResult(1);
		
		if (cartService.checkCart(cartList, cartVO) == false) {
			cartList.add(cartVO);
		}
		
		// 신규 주문 상품 코드 520 중복 주문  => 기대 주문 수량 : 3
		cartVO = new CartVO();
		
		cartVO.setCseq(5);
		cartVO.setId("abcd1234");
		cartVO.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO.setPname("뮬 412123005 (2cm/2colors)");
		cartVO.setPseq(586);
		cartVO.setQuantity(3);
		cartVO.setResult(1);
		
		if (cartService.checkCart(cartList, cartVO) == false) {
			cartList.add(cartVO);
		}
		
		/*
		 * 기대 주문 수량
		 * 520 => 8
		 * 563 => 15
		 * 586 => 3
		 */
		for (CartVO vo : cartList) {
			log.info("vo : " + vo);
		}
		
	} //
}
