package com.javateam.project.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.javateam.project.domain.CartVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CartDAO {

	@Inject
	SqlSession sqlSession;
	
	private static final String MAPPER_NS = "com.javateam.project.mapper.Cart.";
	
	/**
	 * 카트에 상품 담기
	 * 
	 * @param cartVO 카트 VO 객체
	 * @return 카트 담기 성공 여부
	 */
	public boolean insertCart(CartVO cartVO) {
		
		log.info("CartDAO.insertCart");
		return sqlSession.insert(MAPPER_NS + "insertCart", cartVO) == 1 ? true : false;
	} //
	
	/**
	 * 전체 카트 목록 조회
	 * 
	 * @param id 주문자(사용자) 아이디
	 * @return 카트 목록
	 */
	public List<CartVO> listCart(String id) {
		
		log.info("CartDAO.listCart");
		return sqlSession.selectList(MAPPER_NS + "listCart", id);
	}
	
	/**
	 * 카트 수정
	 * 
	 * @param cartVO 카트 VO 객체
	 * @return 카트 수정 성공 여부
	 */
	public boolean updateCart(CartVO cartVO) {
		
		log.info("CartDAO.updateCart");
		return sqlSession.update(MAPPER_NS + "updateCart", cartVO) == 1 ? true : false;
	}
	
	/**
	 * 카트 삭제
	 * 
	 * @param cseq 카트 아이디
	 * @return 카트 삭제 성공 여부
	 */
	public boolean deleteCart(int cseq) {
		
		log.info("CartDAO.deleteCart");
		return sqlSession.delete(MAPPER_NS + "deleteCart", cseq) == 1 ? true : false;
	}
	
}