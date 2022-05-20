package com.javateam.project.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.project.domain.ProductVO;
import com.javateam.project.domain.SearchProductVO;

// import lombok.extern.log4j.Log4j2;
// import lombok.extern.slf4j.Slf4j;

@Repository
// @Slf4j
// @Log4j2
public class ProductDAO {
	
	private static final Logger log
		= LoggerFactory.getLogger(ProductDAO.class);
	
	@Autowired
	SqlSession sqlSession;
	
	private static final String MAPPER_NS 
		= "com.javateam.project.mapper.Product.";
	
	/**
	 * 베스트 상품 리스트 출력
	 * 
	 * @return 상품 객체 리스트
	 */
	public List<ProductVO> listBestProduct() {
		
		log.info("ProductDAO.listBestProduct");
		return sqlSession.selectList(MAPPER_NS + "listBestProduct");
	}

	/**
	 * 신상품 리스트 출력
	 * 
	 * @return 상품 객체 리스트
	 */
	public List<ProductVO> listNewProduct() {
		
		log.info("ProductDAO.listNewProduct");
		return sqlSession.selectList(MAPPER_NS + "listNewProduct");
	}
	
	/**
	 * 종류별 상품 리스트 출력
	 * 
	 * @param kind 상품 종류
	 * @return 상품 객체 리스트
	 */
	public List<ProductVO> listKindProduct(int kind) {
		
		log.info("ProductDAO.listKindProduct");
		return sqlSession.selectList(MAPPER_NS + "listKindProduct", kind);
	}
	
	/**
	 * 개별 상품 출력(조회)
	 * 
	 * @param pseq 상품 아이디
	 * @return 상품 객체
	 */
	public ProductVO getProduct(int pseq) {
		
		log.info("ProductDAO.getProduct");
		return sqlSession.selectOne(MAPPER_NS + "getProduct", pseq);
	}
	
	/**
	 * 개별 상품 등록
	 * 
	 * @param productVO 상품 객체
	 * @return 등록 성공 여부
	 */
	public boolean insertProduct(ProductVO productVO) {
		
		log.info("ProductDAO.insertProduct");
		return sqlSession.insert(MAPPER_NS + "insertProduct", productVO)
				== 1 ? true : false;
	}
	
	/**
	 * 최근 등록 상품 아이디 조회
	 * 
	 * @return 최근 등록 상품 아이디
	 */
	public int getMaxPseq() {
		
		log.info("ProductDAO.getMaxPseq");
		
		return sqlSession.selectOne(MAPPER_NS + "getMaxPseq");
	}
	
	/**
	 * 개별 상품 정보 수정
	 * 
	 * @param productVO 개별 상품 정보
	 * @return 수정 성공 여부
	 */
	public boolean updateProduct(ProductVO productVO) {
		
		log.info("ProductDAO.updateProduct");
		
		return sqlSession.update(MAPPER_NS + "updateProduct", productVO)
  			   == 1 ? true : false;
	}
	
	/**
	 * 전체 상품 조회 (페이징 적용)
	 * 
	 * @param page 현재 페이지
	 * @param limit 페이지당 자료(상품) 수
	 * @return 상품 정보 리스트
	 */
	public List<ProductVO> getProductsByPaging(int page, int limit) {
		
		log.info("ProductDAO.getProductsByPaging");
		
		Map<String, Integer> map = new HashMap<>();
		map.put("page", page);
		map.put("limit", limit);
		
		return sqlSession.selectList(MAPPER_NS + "getProductsByPaging", map);
	}
	
	/**
	 * 총 상품 수 조회
	 * 
	 * @return 총 상품 수
	 */
	public int getTotalProductsCount() {
		
		log.info("ProductDAO.getTotalProductsCount");
		
		return sqlSession.selectOne(MAPPER_NS + "getTotalProductsCount");
	}
	
	/**
	 * 개별 상품 상태(사용/베스트 상품 여부) 수정 
	     ex) 관리자 모드에서 사용/베스트 상품 여부 수정
	     
	 * @param fld 수정할 필드 ex) useyn
	 * @param fldVal 수정할 필드값 ex) "n"
	 * @param pseq 상품 아이디
	 * @return 수정 성공 여부
	 */
	public boolean updateFldByPseq(String fld, String fldVal, int pseq) {
		
		log.info("ProductDAO.updateFldByPseq");
		
		Map<String, Object> map = new HashMap<>();
		map.put("fld", fld);
		map.put("fldVal", fldVal);
		map.put("pseq", pseq);
		
		return sqlSession.update(MAPPER_NS + "updateFldByPseq", map) 
				== 1 ? true : false;
	}
	
	/**
	 * 상품 검색 (페이징)
	 * 
	 * @param seachProductVO 검색 VO
	 * @return 검색 상품들
	 */
	public List<ProductVO> searchProductsByPaging(SearchProductVO searchProductVO) {
		
		log.info("ProductDAO.searchProductByPaging");
		
		return sqlSession.selectList(MAPPER_NS + "searchProductsByPaging", 
							searchProductVO);
	}
	
	/**
	 * 검색 (페이징) 총 상품수
	 * 
	 * @param searchProductVO 검색 VO
	 * @return 총 검색 상품수
	 */
	public int getCountSearchProductsByPaging(SearchProductVO searchProductVO) {
		
		log.info("ProductDAO.getCountSearchProductsByPaging : " + searchProductVO);
			
		return (int)sqlSession.selectOne(MAPPER_NS + "getCountSearchProductsByPaging", 
				searchProductVO);
	}
	
}
