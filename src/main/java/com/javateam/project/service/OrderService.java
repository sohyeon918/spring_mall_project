package com.javateam.project.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.javateam.project.domain.CartVO;
import com.javateam.project.domain.OrderVO;
import com.javateam.project.repository.OrderDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

	@Inject
	OrderDAO orderDAO;
	
	@Inject
	TransactionTemplate txTemplate;
	
	/**
	 * 주문 내역 추가
	 * 
	 * @param cartList 카트 리스트
	 * @param id 사용자 아이디
	 * @return 최근 주문 번호
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int insertOrder(List<CartVO> cartList, String id) {
		
		log.info("OrderService.insertOrder");

		return txTemplate.execute(status -> {
			
			if (orderDAO.insertOrder(id) == true) {
				log.info("주문 내역 추가 성공");
			} else {
				log.error("주문 내역 추가 실패");
			}	
			
			// patch : 2022.5.19
			int maxOseq = orderDAO.getMaxOseq();
			
			for (CartVO cartVO : cartList) {
				
				if (orderDAO.insertOrderDetail(cartVO, maxOseq) == true) {
					log.info("세부 주문 내역 추가 성공");
				} else {
					log.error("세부 주문 내역 추가 실패");
				}
				
			} // for

			return maxOseq;
		});
	}
	
	/**
	 * 주문 현황 조회
	 * 
	 * @param id 사용자 아이디
	 * @param result 미처리(1)/처리(2) 현황
	 * @param oseq 주문 아이디
	 * @return 주문 리스트
	 */
	@Transactional(readOnly=true)
	public List<OrderVO> listOrderById(String id, String result, int oseq) {
		
		log.info("OrderService.listOrderById");
		
		return txTemplate.execute(txStatus-> { 
			return orderDAO.listOrderById(id, result, oseq);
		});
	}
	
	/**
	 * 사용자 개별 진행중인 주문 조회
	 * 
	 * @param id 사용자 아이디
	 * @return 주문 아이디 현황
	 */
	@Transactional(readOnly=true)
	public List<Integer> selectSeqOrderIng(String id) {
		
		log.info("OrderService.selectSeqOrderIng");
		
		return txTemplate.execute(txStatus-> { 
			return orderDAO.selectSeqOrderIng(id);
		});
	}

	
	/**
	 * 관리자 : 주문 현황 조회 
	 * 
	 * @param key 검색키 : 사용자 이름
	 * @return 주문 현황 리스트
	 */
	@Transactional(readOnly=true)
	public List<OrderVO> listOrder(String key) {
		
		log.info("OrderService.listOrder");
		
		return txTemplate.execute(txStatus-> { 
			return orderDAO.listOrder(key);
		});
	}
	
	/**
	 * 관리자 : 주문 처리 상태 수정(갱신)
	 * 
	 * @param odseq 주문 상세 아이디
	 * @return 수정 성공 여부
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean updateOrderResult(int odseq) {
		
		log.info("OrderService.updateOrderResult");
		
		return txTemplate.execute(status -> {

			boolean flag = false;
					
			if (orderDAO.updateOrderResult(odseq) == true) {
				log.info("주문 상태 수정 성공");
				flag = true;
			} else {
				log.error("주문 상태 수정 실패");
				flag = false;
			}	

			return flag;
		});
	}
	
}
