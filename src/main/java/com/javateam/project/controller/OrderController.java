package com.javateam.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.project.domain.CartVO;
import com.javateam.project.domain.MemberVO;
import com.javateam.project.domain.OrderVO;
import com.javateam.project.service.CartService;
import com.javateam.project.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("order")
@Slf4j
public class OrderController {
	
	@Inject
	CartService cartService; // 주문된 카트 상태(result=2)를 수정하기 위한 조치
	
	@Inject
	OrderService orderService;
	
	private static int getCseq(String[] temps, int i) {
		
		int result = 0;
		
		for (String temp : temps) {

			String []arr = temp.split("_");
			
			if (Integer.parseInt(arr[1]) == i) {
				result = Integer.parseInt(arr[0]);
				break;
			} //
		} //
		
		return result;
	}
	
	private static boolean containCseqs(int[] cseqs, int el) {
		
		for (int i : cseqs) {
			if (i == el) {
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping("orderInsert")
	public String orderInsert(@RequestParam(value="cseq", defaultValue="0") int[] cseqs, 
			 				  @RequestParam("cseq_idx") String[] cseqIdxes, 
							  @RequestParam("pseq") int[] pseqs, 
							  @RequestParam("quantity") int[] quantities, 
							  HttpSession session,
							  Model model) 
	{
		log.info("orderInsert");
		
		String id = ((MemberVO)session.getAttribute("LOGIN_USER")).getId();
		
		List<CartVO> cartList = new ArrayList<>();
		CartVO cartVO;
		
		for (int s : cseqs) {
			log.info("- " + s);
		} //
		
		log.info("---------------------");
		
		for (String s : cseqIdxes) {
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
		
		for (int i=0; i<pseqs.length; i++) {
			
			int tempCseq = getCseq(cseqIdxes, i);
			
			// 장바구니에서 실제 주문한 상품 현황
			if (containCseqs(cseqs, tempCseq) == true) {
			
				cartVO = new CartVO();
				cartVO.setCseq(tempCseq);
				cartVO.setId(id);
				cartVO.setPseq(pseqs[i]);
				cartVO.setQuantity(quantities[i]);
				cartVO.setResult(1);

				// 2022.5.20
				// 주문된 상품에 대한 카트 상태 수정(result=1 => 2)
				cartVO.setResult(2); // 이미 주문된 것은 사용자 카트 "화면"에서 삭제 (DB 테이블에서는 정보 유지)
				cartService.updateCart(cartVO);
				
				log.info("cartVO : " + cartVO);
				cartList.add(cartVO);
			} //
			
		} // for	
		
		log.info("-----------------------");
		
		cartList.forEach(x->{ log.info("- " + x); });
		
		/////////////////////////////////////////////
		
		int maxOseq = orderService.insertOrder(cartList, id);		
		
		return "redirect:/order/orderList?oseq="+maxOseq;
	}
	
	@RequestMapping("orderList")
	public String orderlist(@RequestParam("oseq") int oseq,
							HttpSession session,
							Model model) {
		
		log.info("orderList");
		
		String id = ((MemberVO)session.getAttribute("LOGIN_USER")).getId();
		
		List<OrderVO> orderList = orderService.listOrderById(id, "1", oseq);
		
		int totalPrice=0;
		
		for(OrderVO orderVO : orderList){
			totalPrice += orderVO.getPrice2()*orderVO.getQuantity();
		}
	      
		model.addAttribute("orderList", orderList);
		model.addAttribute("totalPrice", totalPrice);
		
		return "/order/order_list";
	}
	
	@RequestMapping("mypage")
	public String mypage(HttpSession session, Model model) {
		
		log.info("mypage");
		
		String id = ((MemberVO)session.getAttribute("LOGIN_USER")).getId();
		
		List<Integer> oseqList = orderService.selectSeqOrderIng(id);
		
		List<OrderVO> orderList = new ArrayList<>();
		
		for (int oseq : oseqList) {
			
			List<OrderVO> orderListIng 
				= orderService.listOrderById(id, "1", oseq);
			
			OrderVO orderVO = orderListIng.get(0);
	        orderVO.setPname(orderVO.getPname() + " 외 "
	            + orderListIng.size() + "건");
	        
	        int totalPrice = 0;
	        
	        for (OrderVO ovo : orderListIng) {
	          totalPrice += ovo.getPrice2() * ovo.getQuantity();
	        }
	        
	        orderVO.setPrice2(totalPrice);
	        orderList.add(orderVO);
		} // for
		
		model.addAttribute("title", "진행 중인 주문 내역");
		model.addAttribute("orderList", orderList);
		
		return "/order/mypage";
	}
	
	@GetMapping("orderDetail")
	public String orderDetail(@RequestParam("oseq") int oseq, 
							  HttpSession session, 
							  Model model) {
		
		log.info("orderDetail");
		
		String id = ((MemberVO)session.getAttribute("LOGIN_USER")).getId();
		List<OrderVO> orderList = orderService.listOrderById(id, "1", oseq);
	      
		int totalPrice=0;
		
		for(OrderVO ovo :orderList){
			totalPrice += ovo.getPrice2() * ovo.getQuantity();
		}
		
		model.addAttribute("orderDetail", orderList.get(0));  
		model.addAttribute("orderList", orderList);
		model.addAttribute("totalPrice", totalPrice);	
		
		return "/order/order_detail";
	}
	
	@GetMapping("orderAll")
	public String orderAll(HttpSession session, HttpServletRequest request) {
		
		log.info("orderAll");
		
		String id = ((MemberVO)session.getAttribute("LOGIN_USER")).getId();
		
		List<Integer> oseqList = orderService.selectSeqOrderIng(id);

		List<OrderVO> orderList = new ArrayList<OrderVO>();

		for (int oseq : oseqList) {
			
			List<OrderVO> orderListIng = orderService.listOrderById(id, "1", oseq);

			OrderVO orderVO = orderListIng.get(0);
			orderVO.setPname(orderVO.getPname() + " 외 "
						   + orderListIng.size() + "건");
			
			log.info("주문일 : " + orderVO.getIndate());

			int totalPrice = 0;
			
			for (OrderVO ovo : orderListIng) {
			  totalPrice += ovo.getPrice2() * ovo.getQuantity();
			}
			
			orderVO.setPrice2(totalPrice);
			orderList.add(orderVO);
		}

		request.setAttribute("title", "총 주문 내역");
		request.setAttribute("orderList", orderList);
		
		return "/order/mypage";
	}

}
