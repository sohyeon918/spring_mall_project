package com.javateam.project.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javateam.project.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("admin")
public class AdminProductRestController {
	
	@Inject
	ProductService productService;

	@GetMapping(value="/product/updateUseYN", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> updateUseYN(@RequestParam("pseq") int pseq,
							  				  @RequestParam("useyn") String useyn) {
		log.info("updateUseYN");
		
		try {
			// 상품 사용 여부 정보 수정 성공했을 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (productService.updateFldByPseq("useyn", useyn, pseq) == true) {
				return new ResponseEntity<String>("상품 사용 여부 정보 수정 성공", HttpStatus.OK);
				
			// 상품 사용 여부 정보 수정 실패했을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				return new ResponseEntity<String>("상품 사용 여부 정보 수정 실패", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("updateUseYN REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	@GetMapping(value="/product/updateBestYN", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> updateBestYN(@RequestParam("pseq") int pseq,
							  				   @RequestParam("bestyn") String bestyn) {
		log.info("updateBestYN");
		
		try {
			// 상품 베스트 상품 여부 정보 수정 성공했을 경우(200)
			// 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			if (productService.updateFldByPseq("bestyn", bestyn, pseq) == true) {
				return new ResponseEntity<String>("상품 베스트 상품 여부 정보 수정 성공", HttpStatus.OK);
				
			// 상품 베스트 상품 여부 정보 수정 실패했을 경우(204)	
			// 구체적인 응답 메시징이 필요할 경우는 200으로 처리 : 이런 경우는 에러로 처리하지 않음.
			} else {
				return new ResponseEntity<String>("상품 베스트 상품 여부 정보 수정 실패", HttpStatus.OK);
			}
			
		} catch (Exception e) {
			log.error("updateBestYN REST Service error : " + e.getMessage());
			// 417(예상 실패): 서버는 Expect 요청 헤더 입력란의 요구사항을 만족할 수 없다.
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		}
		
	}
} //