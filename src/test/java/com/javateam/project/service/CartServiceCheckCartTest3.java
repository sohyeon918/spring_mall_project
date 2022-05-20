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
public class CartServiceCheckCartTest3 {
	
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
		assertNull(cartService.getCartCheckCart(cartList, cartVO)); 
		
//		if (cartService.getCartCheckCart(cartList, cartVO) == null) {
//			cartList.add(cartVO);
//		}
		
	} //
}
