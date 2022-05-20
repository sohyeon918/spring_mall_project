package com.javateam.project.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.project.domain.CartVO;
import com.javateam.project.domain.MemberVO;
import com.javateam.project.service.CartService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("cart")
public class CartController {

	@Inject
	CartService cartService;
	
	@RequestMapping("cartInsert")
	public String cartInsert(@RequestParam("pseq") int pseq,
							 @RequestParam("quantity") int quantity,
							 HttpSession session,
							 Model model) {
		
		log.info("cartInsert : pseq={}, quantity={}", pseq, quantity);
		String errMsg = ""; // 에러 메시지
		String path = "";
		
		MemberVO memberVO = (MemberVO)session.getAttribute("LOGIN_USER");

		CartVO cartVO = new CartVO();
		cartVO.setId(memberVO.getId());
		cartVO.setPseq(pseq);
		cartVO.setQuantity(quantity);
		cartVO.setResult(1); // 주문 상태(1)
		
		// 기존의 카드 현황과 비교하여 중복 상품 점검
		// 중복 상품 있을 경우 updateCart
		// 중복 상품 없을 경우 insertCart
		
		log.info("신규 추가 주문 CartVO : " + cartVO);
		
		List<CartVO> cartList = cartService.listCart(memberVO.getId());
		
		log.info("-------------------");
		
		cartList.forEach(x->{
			log.info("기존 카트 정보 : " + x);
		});
		
		log.info("-------------------");
		
		// 기존 카트에서 중복 상품 없을때
		CartVO checkCartVO = cartService.getCartCheckCart(cartList, cartVO);
		log.info("checkCartVO : " + checkCartVO);
		
		if (checkCartVO == null) {
			
			cartList.add(cartVO);
			
			if (cartService.insertCart(cartVO) == true) {
				
				errMsg = "카트에 상품을 담았습니다.";
				path = "/cart/cartList";
				
			} else {
				
				errMsg = "카트 담기에 실패하였습니다.";
				path = "/product/product_detail?pseq="+pseq;
			} //
		
		// 기존 카트에서 중복 상품이 있을 때	
		} else {
			
			log.info("update 카트 : " +checkCartVO);
			
			if (cartService.updateCart(checkCartVO) == true) {
				
				errMsg = "카트를 수정했습니다.";
				path = "/cart/cartList";
				
			} else {
				
				errMsg = "카트 수정에 실패하였습니다.";
				path = "/product/product_detail?pseq="+pseq;
			} //
			
		} //
		
		model.addAttribute("err_msg", errMsg);
		model.addAttribute("move_path", path);
		
		return "/error/error";
	} //
	
	@GetMapping("cartList")
	public String cartList(HttpSession session, Model model) {
		
		log.info("cartList");
		
		MemberVO memberVO = (MemberVO)session.getAttribute("LOGIN_USER");
		
		List<CartVO> cartList = cartService.listCart(memberVO.getId());
		
		int totalPrice = 0;

		for (CartVO cartVO : cartList) {
			totalPrice += cartVO.getPrice2() * cartVO.getQuantity();
		}
		
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalPrice", totalPrice);
		
		return "/cart/cart_list";
	}
	
	@RequestMapping("cartDelete")
	public String cartDelete(@RequestParam("cseq") int[] cseqs, Model model) {
		
		log.info("cartDelete : ");
		
		String errMsg = "";
		int count = 0; 
		
		for (int s : cseqs) {
			log.info("- " + s);
		} //
		
		for (int cseq : cseqs) {
			
			if (cartService.deleteCart(cseq) == true) {
				count++;
			} 
			
		} // for
		
		if (count == cseqs.length) {
			errMsg = "카트 삭제에 성공하였습니다.";
		} else {
			errMsg = "카트 삭제에 실패하였습니다.";
		} //
		
		model.addAttribute("err_msg", errMsg);
		model.addAttribute("move_path", "/cart/cartList");
		
		return "/error/error";
	}
	
	@RequestMapping("cartUpdate")
	public String cartUpdate(@RequestParam("cseq") int[] cseqs,
							 @RequestParam("pseq") int[] pseqs, 
							 @RequestParam("quantity") int[] quantities, 
							 HttpSession session,
							 Model model) {
		
		log.info("cartUpdate : ");
		
		MemberVO memberVO = (MemberVO)session.getAttribute("LOGIN_USER");
		String id = memberVO.getId();
		
		String errMsg = "";
		int count = 0; 
		
		CartVO cartVO;
		
		for (int s : cseqs) {
			log.info("- " + s);
		} //
		
		log.info("---------------------");
		
		for (int s : pseqs) {
			log.info("- " + s);
		} //
		
		log.info("---------------------");
		
		for (int s : quantities) {
			log.info("- " + s);
		} //
		
		log.info("---------------------");

		
 		// map.entrySet().forEach(m->{ log.info("- : "+m);});
		// Map => List<CartVO>
		
		
		for (int i=0; i<cseqs.length; i++) {
			
			cartVO = new CartVO();
			cartVO.setCseq(cseqs[i]);
			cartVO.setId(id);
			cartVO.setPseq(pseqs[i]);
			cartVO.setQuantity(quantities[i]);
			cartVO.setResult(1);
			
			log.info("cartVO : " + cartVO);
			
			if (cartService.updateCart(cartVO) == true) {
				count++;
			} 
			
		} // for
		
		if (count == cseqs.length) {
			errMsg = "카트 수정에 성공하였습니다.";
		} else {
			errMsg = "카트 수정에 실패하였습니다.";
		} //
		
		model.addAttribute("err_msg", errMsg);
		model.addAttribute("move_path", "/cart/cartList");
		
		return "/error/error";
	}
	
}
