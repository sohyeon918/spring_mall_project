package com.javateam.project.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.project.domain.CartVO;
import com.javateam.project.domain.OrderVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class OrderDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	private static final String MAPPER_NS = "com.javateam.project.mapper.Order.";
	
	/**
	 * 최근 주문 내역의 기본키(최근 주문 번호)
	 * 
	 * @return 최근 주문 번호
	 */
	public int getMaxOseq() {
		
		log.info("OrderDAO.getMaxOseq");
		
		return sqlSession.selectOne(MAPPER_NS + "getMaxOseq");
	} //
	
	
	/**
	 * 주문 내역 추가
	 *  
	 * @param id 사용자 아이디
	 * @return 주문 추가 성공 여부
	 */
	public boolean insertOrder(String id) {

		log.info("OrderDAO.insertOrder");
		return sqlSession.insert(MAPPER_NS + "insertOrder", id) == 1 ? true : false;
	}

	/**
	 * 주문 상세 내역 추가
	 * 
	 * @param cartVO 카트 VO 객체
	 * @param 최근 주문 번호
	 * @return 주문 상세 내역 추가 성공 여부
	 */
	public boolean insertOrderDetail(CartVO cartVO, int maxOseq) {
		
		log.info("OrderDAO.insertOrderDetail");
		Map<String, Object> map = new HashMap<>();
		map.put("cartVO", cartVO);
		map.put("maxOseq", maxOseq);
		
		return sqlSession.insert(MAPPER_NS + "insertOrderDetail", map) == 1 ? true : false;
	}
	
	/**
	 * 주문 현황 조회
	 * 
	 * @param id 사용자 아이디
	 * @param result 미처리(1)/처리(2) 현황
	 * @param oseq 주문 아이디
	 * @return 주문 리스트
	 */
	public List<OrderVO> listOrderById(String id, String result, int oseq) {
		
		log.info("OrderDAO.listOrderById");
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("result", result);
		map.put("oseq", oseq);
		
		return sqlSession.selectList(MAPPER_NS + "listOrderById", map);
	}

	/**
	 * 사용자 개별 진행중인 주문 조회
	 * 
	 * @param id 사용자 아이디
	 * @return 주문 아이디 현황
	 */
	public List<Integer> selectSeqOrderIng(String id) {
		
		log.info("OrderDAO.selectOseqOrderIng");
		return sqlSession.selectList(MAPPER_NS + "selectSeqOrderIng", id);
	}
	
	/**
	 * 관리자 : 주문 현황 조회 
	 * 
	 * @param key 검색키 : 사용자 이름
	 * @return 주문 현황 리스트
	 */
	public List<OrderVO> listOrder(String key) {
		
		log.info("OrderDAO.listOrder");
		return sqlSession.selectList(MAPPER_NS + "listOrder", key);
	}
	
	/**
	 * 관리자 : 주문 처리 상태 수정(갱신)
	 * 
	 * @param odseq 주문 상세 아이디
	 * @return 수정 성공 여부
	 */
	public boolean updateOrderResult(int odseq) {
		
		log.info("OrderDAO.updateOrderResult");
		return sqlSession.update(MAPPER_NS + "updateOrderResult", odseq) == 1 ? true : false;
	}
	
}
