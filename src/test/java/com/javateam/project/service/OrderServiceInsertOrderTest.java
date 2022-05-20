package com.javateam.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javateam.project.domain.CartVO;
import com.javateam.project.repository.OrderDAO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/db-context.xml"})
@Slf4j
public class OrderServiceInsertOrderTest {
	
	@Inject
	OrderService orderSvc;
	
	@Inject
	OrderDAO orderDAO;

	
	@Test
	public void test() {
		
		log.info("OrderServiceInsertOrderTest");
		
		List<CartVO> cartList = new ArrayList<>();
		
		CartVO cartVO = new CartVO();
		cartVO.setCseq(1);
		cartVO.setId("abcd1234");
		cartVO.setIndate(new Timestamp(System.currentTimeMillis()));
		cartVO.setPname("슬리퍼 412123024 (6.5cm/3colors)");
		cartVO.setPseq(563);
		cartVO.setQuantity(10);
		cartVO.setResult(1);
		
		cartList.add(cartVO);
		
		assertEquals(orderDAO.getMaxOseq()+1, orderSvc.insertOrder(cartList, "spring1234"));
		
	} //
}
