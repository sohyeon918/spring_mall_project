package com.javateam.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.javateam.project.domain.CartVO;
import com.javateam.project.repository.CartDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartService {
	
	@Inject
	CartDAO cartDAO;
	
	@Autowired
	TransactionTemplate txTemplate;
	
	/**
	 * 상품을 카트에 담을 경우 중복 상품 주문 점검
	 * 
	 * ex) 날짜와 무관하게 아직 주문 미처리(result=1)인 동일 상품(pseq) 주문시
	 * 주문수량 조정(증감)   
	 * 
	 * @param cartList 카트(장바구니) 리스트
	 * @return 중복 주문 상품 존재 여부
	 */
	public boolean checkCart(List<CartVO> cartList, CartVO cartVO) {
		
		log.info("CartService.checkCart");
		boolean flag = false;
		
		// 동일 여부 점검 필드들(id, pseq, result=1)
		
		for (CartVO defaultCartVO : cartList) {
			
			if (defaultCartVO.equals(cartVO)) {
			
				log.info("기존 {}상품과 동일한 신규 {}상품 주문 : pseq={}", 
						defaultCartVO.getPseq(), cartVO.getId(), cartVO.getPseq());
				// 동일 상품의 주문 수량만 변경
				
				log.info("defaultCartVO 기존 수량 : " + defaultCartVO.getQuantity());
				log.info("CartVO.getQuantity() : " + cartVO.getQuantity());
				
				defaultCartVO.setQuantity(defaultCartVO.getQuantity() + cartVO.getQuantity());
				log.info("defaultCartVO 변경 수량 : " + defaultCartVO.getQuantity());
				flag = true;
				break;
			}
			
		} // for
		
		return flag;
	}
	
	/**
	 * 상품을 카트에 담을 경우 중복 상품 주문 점검
	 * 
	 * ex) 날짜와 무관하게 아직 주문 미처리(result=1)인 동일 상품(pseq) 주문시
	 * 주문수량 조정(증감)   
	 * 
	 * @param cartList 카트(장바구니) 리스트
	 * @return 중복 주문 상품 Cart VO 객체
	 */
	public CartVO getCartCheckCart(List<CartVO> cartList, CartVO cartVO) {
		
		log.info("CartService.getCartCheckCart");
		CartVO resultCartVO = null;
		
		// 동일 여부 점검 필드들(id, pseq, result=1)
		
		for (CartVO defaultCartVO : cartList) {
			
			if (defaultCartVO.equals(cartVO)) {
			
				log.info("기존 {}상품과 동일한 신규 {}상품 주문 : pseq={}", 
						defaultCartVO.getPseq(), cartVO.getId(), cartVO.getPseq());
				// 동일 상품의 주문 수량만 변경
				
				log.info("defaultCartVO 기존 수량 : " + defaultCartVO.getQuantity());
				log.info("CartVO.getQuantity() : " + cartVO.getQuantity());
				
				defaultCartVO.setQuantity(defaultCartVO.getQuantity() + cartVO.getQuantity());
				log.info("defaultCartVO 변경 수량 : " + defaultCartVO.getQuantity());
				
				resultCartVO = defaultCartVO;
				
				break;
			}
			
		} // for
		
		return resultCartVO;
	}
	
	/**
	 * 카트에 상품 담기
	 * 
	 * @param cartVO 카트 VO 객체
	 * @return 카트 담기 성공 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertCart(CartVO cartVO) {
		
		log.info("CartService.insertCart");
		
		return txTemplate.execute(status -> {
			
			boolean flag = false;
			
			try {
				
				if (cartDAO.insertCart(cartVO) == true) {
					log.info("카트 저장 성공");
					flag = true;
				} else {
					log.info("카트 저장 실패");
					throw new Exception("카트 저장 실패");
				}
				
			} catch (Exception e) {					
				status.setRollbackOnly();
				flag = false;
				log.error("CartService.insertCart error : " + e.getMessage());
			} //
			
			return flag;
		});
	} //
	
	/**
	 * 전체 카트 목록 조회
	 * 
	 * @param id 주문자(사용자) 아이디
	 * @return 카트 목록
	 */
	@Transactional(readOnly=true)
	public List<CartVO> listCart(String id) {
		
		log.info("CartService.listCart");
		
		return txTemplate.execute(txStatus-> { 
			return cartDAO.listCart(id);
		});
	}

	/**
	 * 카트 수정
	 * 
	 * @param cartVO 카트 VO 객체
	 * @return 카트 수정 성공 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateCart(CartVO cartVO) {
		
		log.info("CartService.updateCart");
		
		return txTemplate.execute(status -> {
			
			boolean flag = false;
			
			try {
				
				if (cartDAO.updateCart(cartVO) == true) {
					log.info("카트 수정 성공");
					flag = true;
				} else {
					log.info("카트 수정 실패");
					throw new Exception("카트 수정 실패");
				}
				
			} catch (Exception e) {					
				status.setRollbackOnly();
				flag = false;
				log.error("CartService.updateCart error : " + e.getMessage());
			} //
			
			return flag;
		});
	}
	
	/**
	 * 카트 삭제
	 * 
	 * @param cseq 카트 아이디
	 * @return 카트 삭제 성공 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean deleteCart(int cseq) {
		
		log.info("CartService.deleteCart");
		
		return txTemplate.execute(status -> {
			
			boolean flag = false;
			
			if (cartDAO.deleteCart(cseq) == true) {
				log.info("카트 삭제 성공");
				flag = true;
			} else {
				log.error("카트 삭제 실패");
			}
			
			return flag;
		});
	} //
	
}