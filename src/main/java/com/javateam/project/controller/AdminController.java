package com.javateam.project.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.javateam.project.domain.MemberRoleVO;
import com.javateam.project.domain.MemberVO;
import com.javateam.project.domain.PageVO;
import com.javateam.project.domain.ProductDTO;
import com.javateam.project.domain.ProductVO;
import com.javateam.project.domain.SearchProductVO;
import com.javateam.project.domain.SearchVO;
import com.javateam.project.service.FileNamingService;
import com.javateam.project.service.MemberService;
import com.javateam.project.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Controller
@Slf4j
@RequestMapping("admin")
// @SessionAttributes("productVO") // 기존 상품 정보 세션화(다른 방법)
public class AdminController {
	
	@Inject
	MemberService memberService;
	
	@Inject
	ProductService productService;
	
	@Inject
	FileNamingService fileNamingService;
	
	@Inject
	FileSystemResource uploadDirResource;
	
	@RequestMapping("home")
	public String home() {
		
		log.info("admin home");
		
		// return "/admin/home";
		return "redirect:/admin/member/memberList";
	} //
	
	@RequestMapping("/member/memberList")
	public String memberList(@RequestParam(value="page", defaultValue="1") int page, 
							 @RequestParam(value="limit", defaultValue="10") int limit,
							 Model model)
	{
		log.info("admin memberList");
		
		List<MemberRoleVO> members = memberService.getMembersByPaging(page, limit);
		
		// 총 회원 수
		int totalMembersCount = memberService.getTotalMembersCount();
		
		// 마지막 페이지
		int startPage = 1;
		int maxPage = (int)((double)totalMembersCount/limit + 0.95); 
		
		PageVO pageVO = new PageVO();
		pageVO.setCurrPage(page);
		pageVO.setStartPage(startPage);		
		pageVO.setEndPage(maxPage);
		pageVO.setLimit(limit);
		pageVO.setMaxPage(maxPage);
		
		model.addAttribute("members", members);
		model.addAttribute("pageVO", pageVO);
		
		return "/admin/member/member_list";
		// return "/admin/member/member_list_color";
	} //
	
	@RequestMapping("/member/searchMemberList")
	public String searchMemberList(@RequestParam(value="page", defaultValue="1") int page, 
								   @RequestParam(value="limit", defaultValue="10") int limit,
								   @RequestParam("search_field") String searchFld,
								   @RequestParam("search_word") String searchWord,
								   Model model)
	{
		log.info("admin searchMemberList");
		
		SearchVO searchVO = new SearchVO();
		searchVO.setLimit(limit);
		searchVO.setPage(page);
		searchVO.setSearchFld(searchFld);
		// 검색어 현실화 : 좌우 공백 제거, 중간 공백 2칸 이상 => 1칸으로 축소
		searchVO.setSearchWord(searchWord.trim().replaceAll("[ ]{2,}", " ")); 
		
		log.info("---- searchVO : " + searchVO);
		
		List<MemberRoleVO> members = memberService.searchMembersByPaging(searchVO);
		
		// 총 검색 회원 수
		int totalMembersCount = memberService.getCountSearchMembersByPaging(searchVO);
		
		// 마지막 페이지
		int startPage = 1;
		int maxPage = (int)((double)totalMembersCount/limit + 0.95); 
		
		PageVO pageVO = new PageVO();
		pageVO.setCurrPage(page);
		pageVO.setStartPage(startPage);		
		pageVO.setEndPage(maxPage);
		pageVO.setLimit(limit);
		pageVO.setMaxPage(maxPage);
		
		model.addAttribute("members", members);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("searchVO", searchVO);
		
		return "/admin/member/member_list";
	} //
	
	@GetMapping("/product/productList")
	public String productList(@RequestParam(value="page", defaultValue="1") int page, 
							  @RequestParam(value="limit", defaultValue="5") int limit,
							  Model model) 
	{
		log.info("admin productList");
		
		List<ProductVO> products = productService.getProductsbyPaging(page, limit);
		
		// 총 상품 수
		int totalProductsCount = productService.getTotalProductsCount();
		
		// 마지막 페이지
		int startPage = 1;
		int maxPage = (int)((double)totalProductsCount/limit + 0.95);
		
		PageVO pageVO = new PageVO();
		pageVO.setCurrPage(page);
		pageVO.setStartPage(startPage);		
		pageVO.setEndPage(maxPage);
		pageVO.setLimit(limit);
		pageVO.setMaxPage(maxPage);
		
		model.addAttribute("products", products);
		model.addAttribute("pageVO", pageVO);
		
		model.addAttribute("page_title", "상품 리스트");
		// model.addAttribute("css_file", "product_regist.css");
		model.addAttribute("js_file", "product_list.js");
		
		return "/admin/product/product_list";
	}
	
	@GetMapping("/product/searchProductList")
	public String searchProductList(@RequestParam(value="page", defaultValue="1") int page, 
								    @RequestParam(value="limit", defaultValue="5") int limit,
								    @RequestParam("search_field") String searchFld,
								    @RequestParam(value="search_field_val", defaultValue="") String searchFldVal,
								    @RequestParam("search_word") String searchWord,
								    Model model) 
	{
		log.info("admin searchProductList");
		
		log.info("search_fld : " + searchFld);
		log.info("search_fld_val : " + searchFldVal);
		log.info("search_word : " + (searchWord==null ? "null" : searchWord));
		
		// String searchFldVal = null;

		// (상품 분류 무관) 전체 상품에서 검색
		// searchFld == null and searchWord != null
		if (searchFld.equals("all")) {
			
			// searchFld = null;			
			searchFldVal = searchWord;
		
		// 상품 분류(category) 상품(검색어)을 검색	
		// searchFld != null and searchWord != null	
		} else {
			
			// 
			// searchFldVal = searchFld; // kind = 1
			if (!searchFld.equals("kind")) {
				
				searchFldVal = searchFld;
				searchFld = "kind";
			}	
			
		} //
		
		SearchProductVO searchProductVO = new SearchProductVO();
		searchProductVO.setLimit(limit);
		searchProductVO.setPage(page);
		searchProductVO.setSearchFld(searchFld);
		searchProductVO.setSearchFldVal(searchFldVal); //
		
		// 검색어 현실화 : 좌우 공백 제거, 중간 공백 2칸 이상 => 1칸으로 축소
		searchProductVO.setSearchWord(searchWord.trim().replaceAll("[ ]{2,}", " ")); 
		
		log.info("---- searchProductVO : " + searchProductVO);
		
		List<ProductVO> products = productService.searchProductsByPaging(searchProductVO);
		
		// 검색 총 상품 수
		// int totalProductsCount = productService.getTotalProductsCount();
		int totalProductsCount = productService.getCountSearchProductsByPaging(searchProductVO);
		
		// 마지막 페이지
		int startPage = 1;
		int maxPage = (int)((double)totalProductsCount/limit + 0.95);
		
		PageVO pageVO = new PageVO();
		pageVO.setCurrPage(page);
		pageVO.setStartPage(startPage);		
		pageVO.setEndPage(maxPage);
		pageVO.setLimit(limit);
		pageVO.setMaxPage(maxPage);
		
		model.addAttribute("products", products);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("searchProductVO", searchProductVO);
		
		model.addAttribute("page_title", "상품 리스트");
		// model.addAttribute("css_file", "product_regist.css");
		model.addAttribute("js_file", "product_list.js");
				
		return "/admin/product/product_list";
	}
	
	@GetMapping("/product/productRegist")
	public String productRegist(Model model) {
		
		log.info("admin productRegist");
		
		model.addAttribute("page_title", "상품 등록");
		// model.addAttribute("css_file", "product_regist.css");
		model.addAttribute("js_file", "product_regist.js");
		
		return "/admin/product/product_regist";
	}
	
	@PostMapping("/product/productRegistProc")
	public String productRegistProc(ProductDTO productDTO, Model model) {
		
		log.info("admin productRegistProc : {}", productDTO);
		
		// 파일 저장 메시지
		String msg = "";
		// 저장 조치(성공/실패) 이후 이동 페이지
		String movePath = "";
		
		// 유효 파일 점검(빈(blank) 파일 점검)
		// 유효 파일 이면...
		if (!productDTO.getImage().isEmpty() &&
			 productDTO.getImage() != null &&
			 productDTO.getImage().getSize() > 0) 
		{
			
			// 파일 암호화(UUID 활용)
			// ex) 118d9e17-af23-4004-a105-601512866fbe_jquery-3.6.0.js
			MultipartFile file = productDTO.getImage();
			String fileName = file.getOriginalFilename();
			String encodingFileName = fileNamingService.enFilenameUUID(fileName);
			
			// 파일 저장 경로
			// ex) E:\\lsh\\works\\project2\\upload\\product_images 
			String savePath = uploadDirResource.getPath().replace("/", "\\") + "\\product_images\\";
			
			log.info("savePath : " + savePath);
			log.info("encodingFileName : " + encodingFileName);
			
			// 저장
			try {
					// 원본 이미지 파일 저장
					Files.write(Paths.get(savePath + encodingFileName), file.getBytes());
					
					File outFileName = new File(savePath + encodingFileName);
					
					// 썸네일 이미지 파일 저장 : 190 * 200(px)
					Thumbnails.of(outFileName)
			       	 		  .size(190, 200)
			       	 		  .keepAspectRatio(false)
				 			  // .outputFormat("png")
			       	 		  .toFiles(new File(savePath + "\\thumbnails\\"), 
			       	 				   Rename.PREFIX_HYPHEN_THUMBNAIL); 
				
			} catch (IOException e) {
				log.error("업로드 이미지 파일 저장 오류");
				msg = "업로드 이미지 파일 저장 오류";
				movePath = "/admin/product/productRegist";
			}
			
			// DB(상품 테이블)에 저장
			// DTO => VO			
			ProductVO productVO = new ProductVO(productDTO);
			productVO.setImage(encodingFileName);
			productVO.setThumbImage("thumbnail-"+encodingFileName);
			
			if (productService.insertProduct(productVO) == true) {
			
				msg = "개별 상품 등록에 성공하였습니다";
				
				int pseq = productService.getMaxPseq();
				
				log.info("현재 등록된 상품 아이디 : " + pseq);
				
				movePath = "/admin/product/productDetail?pseq="+pseq;
			
			} else {
				
				msg = "DB 오류로 파일 업로드에 실패하였습니다";
				movePath = "/admin/product/productRegist";
			} //			
			
		} else {
			
			log.info("비정상(비어있는) 파일입니다");
			msg = "비정상(비어있는) 파일입니다";
			movePath = "/admin/product/productRegist";
			
		} //
		
		model.addAttribute("err_msg", msg);
		model.addAttribute("move_path", movePath);
		
		return "/error/error";
	}
	
	@GetMapping("/product/productDetail")
	public String productDetail(@RequestParam("pseq") int pseq, 
								@RequestParam(value="page", defaultValue="1") int page, 
								Model model) {
		
		log.info("admin productDetail : pseq={}", pseq);
		
		// 개별 상품 정보 조회
		ProductVO productVO = productService.getProduct(pseq);
		
		model.addAttribute("product", productVO); // 개별 상품 정보
		model.addAttribute("page_title", "개별 상품 정보 조회"); // 페이지 타이틀
		model.addAttribute("page", page);
		
		return "/admin/product/product_detail";
	}
	
	@GetMapping("/product/productUpdate")
	public String productUpdate(@RequestParam("pseq") int pseq,
								HttpSession session,
								Model model) {
		
		log.info("admin productUpdate : pseq={}", pseq);
		
		// 기존 개별 상품 정보 조회 
		ProductVO productVO = productService.getProduct(pseq);
		
		// 기존 정보를 세션(session)화
		if (session.getAttribute("DEFAULT_PRODUCT_INFO") == null) {
			session.setAttribute("DEFAULT_PRODUCT_INFO", productVO);
		}
		
		model.addAttribute("product", productVO); // 개별 상품 정보
		model.addAttribute("page_title", "개별 상품 정보 수정"); // 페이지 타이틀
		model.addAttribute("js_file", "product_update.js");
		
		return "/admin/product/product_update";
	}
	
	@PostMapping("/product/productUpdateProc")
	public String productUpdateProc(ProductDTO productDTO, 
									HttpSession session,
									Model model) {
		
		/** 신규 이미지 파일 객체 비어 있는지 여부  */
		boolean isFileEmpty = productDTO.getImage().isEmpty();
		
		/** 수정할 상품 아이디 */
		int pseq = productDTO.getPseq();
		
		log.info("admin productUpdateProc : {}", productDTO);
		log.info("수정 이미지 파일 객체 : {}", productDTO.getImage());
		log.info("수정 이미지 파일 객체 비어 있는지 여부 : {}", isFileEmpty);
		log.info("수정 이미지 파일 원래 이름 : {}", productDTO.getImage().getOriginalFilename());
		
		// 파일 저장 메시지
		String msg = "";
		// 저장 조치(성공/실패) 이후 이동 페이지
		String movePath = "";
				
		// 이미지 파일 수정(업로드) 여부
		// 비어 있는지 여부 점검 => 참 : 파일 업로드(X), 업데이트(기존 보유 파일 그대로 유지)
		
		// 기존 정보와 수정 정보와 비교 
		// case : 기존 정보 변경(X) -> 수정 불필요
		// case : 기존 정보 변경(O) -> 수정 !		
		
		// 기존 정보 : 세션 활용 ex) DB 절약
		// ProductVO productVO = productService.getProduct(productDTO.getPseq());
		ProductVO productVO = (ProductVO)session.getAttribute("DEFAULT_PRODUCT_INFO");
		ProductDTO oldProductDTO = new ProductDTO(productVO);
		
		log.info("기존 상품 정보(VO) : " + productVO);
		log.info("기존 상품 정보(DTO) : " + oldProductDTO);
		log.info("수정 상품 정보 : " + productDTO);
		
		log.info("수정/기존 일치성 여부(파일 업로드 비교 제외) : " + productDTO.equals(oldProductDTO));
		
		// 업로드 파일 정보를 제외하고 비교
		// 1) 업로드 파일이 "없는" 상태에서 기존 정보와 동일
		// 2) 업로드 파일이 "있는" 상태에서 타 정보가 기존 정보와 동일
		if (productDTO.equals(oldProductDTO) == true && isFileEmpty == true) {
			
			log.info("기존/수정 정보 일치 => 수정 불필요");
			msg = "수정할 정보가 기존 정보와 동일합니다"; // DB 작업 생략(절약)
			
			// 다시 수정 페이지로 이동 : 재수정할 기회 부여
			movePath = "/admin/product/productUpdate?pseq="+pseq;
			
			// 개별 상품 정보 조회 페이지로 이동
			// movePath = "/admin/product/productDetail?pseq="+pseq;
			
		} else {
			
			log.info("기존/수정 정보 불일치 => 수정 필요 !");
			
			// DTO => VO
			String tempImage = productVO.getImage();
			String tempThumbImage = productVO.getThumbImage();
			
			// 기존 업로드 파일들 보존
			productVO = new ProductVO(productDTO);
			productVO.setImage(tempImage);
			productVO.setThumbImage(tempThumbImage);
			
			// 파일 업로드
			// 유효 파일 점검(빈(blank) 파일 점검)
			// 유효 파일 이면...
			if (!productDTO.getImage().isEmpty() &&
				 productDTO.getImage() != null &&
				 productDTO.getImage().getSize() > 0) 
			{
				// 파일 암호화(UUID 활용)
				// ex) 118d9e17-af23-4004-a105-601512866fbe_jquery-3.6.0.js
				MultipartFile file = productDTO.getImage();
				String fileName = file.getOriginalFilename();
				String encodingFileName = fileNamingService.enFilenameUUID(fileName);
				
				// 파일 저장 경로
				// ex) E:\\lsh\\works\\project2\\upload\\product_images 
				String savePath = uploadDirResource.getPath().replace("/", "\\") + "\\product_images\\";
				
				log.info("savePath : " + savePath);
				log.info("encodingFileName : " + encodingFileName);
				
				// 저장
				// 주의) 기존 업로드 파일 처리 문제
				// ex) 
				// 1) 즉각 처리
				// 2) 일괄 처리(batch) : 사용자 유휴시간(심야) 활용 일괄 삭제처리, 
				//    파일 보존 기한(기존 파일 10일간 보관) => 기한 초과시 일괄 삭제 처리
				try {
						// 기존 파일들 삭제(즉각 처리) : 원본 + 썸네일
						Files.deleteIfExists(Paths.get(savePath + productVO.getImage()));
						Files.deleteIfExists(Paths.get(savePath + "\\thumbnails\\" + productVO.getThumbImage())); 
						
						// 원본 이미지 파일 저장(수정본)
						Files.write(Paths.get(savePath + encodingFileName), file.getBytes());
						
						File outFileName = new File(savePath + encodingFileName);
						
						// 썸네일 이미지 파일 저장 : 190 * 200(px)
						Thumbnails.of(outFileName)
				       	 		  .size(190, 200)
				       	 		  .keepAspectRatio(false)
				       	 		  .toFiles(new File(savePath + "\\thumbnails\\"), 
				       	 				   Rename.PREFIX_HYPHEN_THUMBNAIL); 
					
				} catch (IOException e) {
					log.error("업로드 이미지 파일(수정) 저장 오류");
					msg = "업로드 이미지 파일(수정) 저장 오류";
					movePath = "/admin/product/productUpdate?pseq="+pseq;
				}
				
				// 업로드 파일이 있을 경우
				productVO.setImage(encodingFileName);
				productVO.setThumbImage("thumbnail-"+encodingFileName);
				
			} else {
					
				log.info("비정상(비어있는) 파일입니다");
				msg = "비정상(비어있는) 파일입니다";
				movePath = "/admin/product/productUpdate?pseq="+pseq;
				
			} // 파일 업로드
			
			log.info("DB 저장 전 최종 VO : {}", productVO);
			
			// DB 저장
			if (productService.updateProduct(productVO) == true) {
				
				msg = "개별 상품 수정에 성공하였습니다";
				// 개별 상품 조회 페이지로 이동
				movePath = "/admin/product/productDetail?pseq="+pseq;
				
			} else {
				
				msg = "DB 오류로 파일 업로드에 실패하였습니다";
				movePath = "/admin/product/productUpdate?pseq="+pseq;
			} // DB 저장
			
		} //

		// 기존 상품 정보 세션 제거
		if (session.getAttribute("DEFAULT_PRODUCT_INFO") != null) {
			session.removeAttribute("DEFAULT_PRODUCT_INFO");
		}
		
		model.addAttribute("err_msg", msg);
		model.addAttribute("move_path", movePath);
		
		return "/error/error";
	}	
	
}