package com.javateam.project.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.project.domain.ProductVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Service
@Slf4j
public class ShoeCrawlService {
	
	@Autowired
	FileNamingService fileNamingService;
	
	// @Autowired
	FileSystemResource uploadDirResource = new FileSystemResource("E:/lsh/works/project2/upload/");

	@Inject
	ProductService productService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean generateShoeProductsDummy(int kind) throws IOException {
		
		log.info("ShoeCrawlService.generateShoeProductsDummy");
		boolean flag = false;

		String targetImagePath = uploadDirResource.getPath().replace("/", "\\") + "\\product_images\\";
		log.info("파일 저장 경로 : " + targetImagePath.replace("/", "\\") + "\\product_images\\");

		
		// 상품 갤러리 : <div class="item_gallery_type">
		/// https://www.saltandchocolate.co.kr/goods/goods_view.php?goodsNo=1000000527
		// String url = "https://www.saltandchocolate.co.kr/goods/goods_view.php?goodsNo=1000000527";
		// ex) 부츠("2") 카테고리 : https://www.saltandchocolate.co.kr/goods/goods_list.php?cateCd=001014
		
		// https://www.saltandchocolate.co.kr/goods/goods_list.php?cateCd=001014
		// 카테고리 코드 : cateCd
		Map<Integer, String> kinds = new HashMap<>();
		kinds.put(1, "001011"); // 힐 <= 펌프스
		kinds.put(2, "001014"); // 부츠
		kinds.put(3, "001021"); // 샌들/슬리퍼
		kinds.put(4, "001019"); // 블로퍼/뮬
		kinds.put(5, "001017"); // 슬링백
		// kinds.put(6, ""); // 세일
		
		// String url = "https://www.saltandchocolate.co.kr/goods/goods_list.php?cateCd=001014"; // 부츠(O)
		// String url = "https://www.saltandchocolate.co.kr/goods/goods_list.php?cateCd=001021"; // 샌들/슬리퍼(O)
		// String url = "https://www.saltandchocolate.co.kr/goods/goods_list.php?cateCd=001019"; // 블로퍼/뮬(O)
		// String url = "https://www.saltandchocolate.co.kr/goods/goods_list.php?cateCd=001013"; // 스니커즈 (문제 발생 : tx rollback)
		// String url = "https://www.saltandchocolate.co.kr/goods/goods_list.php?cateCd=001017"; // 슬링백
		// String url = "https://www.saltandchocolate.co.kr/goods/goods_list.php?cateCd=001011"; // 힐(펌프스)

		String url = "https://www.saltandchocolate.co.kr/goods/goods_list.php?cateCd="+kinds.get(kind);
		
		log.info("url : " + url);
		
		Document doc = Jsoup.connect(url).get();
		ProductVO productVO;
		String imgURL;
		String saveImageFileName;
		String imageExtName;

		// 상품 코드
		// <div class="item_photo_box" data-image-list = "/data/goods/21/12/52//1000000570/1000000570_list_011.jpg" data-image-main = "/data/goods/21/12/52//1000000570/1000000570_main_030.jpg" data-image-detail = "/data/goods/21/12/52//1000000570/1000000570_detail_032.jpg" data-image-magnify = "/data/goods/21/12/52//1000000570/1000000570_magnify_089.jpg">
        //     <a href="../goods/goods_view.php?goodsNo=1000000570" >
		
		// 상품리스트  정보
		Elements rawProducts = doc.select("div[class='item_cont']");
		Element rawProduct;
		String productId;
		
		for (int i=0; i<rawProducts.size(); i++) {

			rawProduct = rawProducts.get(i).getAllElements().select("div[class='item_photo_box']").get(0);
			
			// <a href="../goods/goods_view.php?goodsNo=1000000570" >
			// System.out.println(rawProduct.getElementsByTag("a").get(0).attr("href"));
			
			// 상품 코드
			String productIdAnchor = rawProduct.getElementsByTag("a").get(0).attr("href");
			productId = productIdAnchor.substring(productIdAnchor.indexOf("goodsNo=") + "goodsNo=".length());
			
			System.out.println("productId : " + productId);
			
			// 상품명
			String productName = rawProduct.getElementsByTag("img").get(0).attr("title");
			
			System.out.println("productName : " + productName);
			
			// 상품 정가
			int price1 = Integer.parseInt(rawProducts.get(i).getAllElements()
													 .select("div[class='item_money_box'] [class='item_fixedPrice']")
													 .html().replaceAll("[원, ]", ""));
			
			log.info("product price1(정가) : " + price1);

			
			// 상품 할인가(판매가)
			int price2 = Integer.parseInt(rawProducts.get(i).getAllElements()
													 .select("div[class='item_money_box'] [class='item_price'] span")
													 .html().replaceAll("[원, ]", "")); 
			
			log.info("product price2(판매가) : " + price2);
			
			// 상품 이미지 : 약간 큰 것 
			// https://www.saltandchocolate.co.kr/goods/goods_view.php?goodsNo=1000000570
			// https://www.saltandchocolate.co.kr/data/goods/21/09/37//1000000527/modify_detail_069.jpg
			
			// 이미지 주소
			//  <meta property="og:image" content="
			//  <meta property="og:image" content="https://www.saltandchocolate.co.kr/data/goods/21/09/37//1000000527/modify_detail_069.jpg">
			
			String imgPageURL = "https://www.saltandchocolate.co.kr/goods/goods_view.php?goodsNo="+productId;
			Document docImg = Jsoup.connect(imgPageURL).get();
			
			imgURL = docImg.select("meta[property='og:image']").attr("content");
			
			imageExtName = imgURL.substring(imgURL.lastIndexOf('.'));
			saveImageFileName = productId; // 상품 아이디로 파일명 저장
			
			// 원본 이미지 파일 저장 : 파일 암호화 서비스
			String encodedFileName = fileNamingService.enFilenameUUID(saveImageFileName + imageExtName);
			
			log.info("targetImagePath : " + targetImagePath);
			
			InputStream in = new URL(imgURL).openStream();
			Files.copy(in, Paths.get(targetImagePath + encodedFileName), StandardCopyOption.REPLACE_EXISTING);
			
			// 썸네일 이미지 저장
			// 썸네일 이미지 파일 저장 : 190 * 200(px)
			
			try {
				
				File file = new File(targetImagePath + encodedFileName);
				
				Thumbnails.of(file)
		       	 		  .size(190, 200)
		       	 		  .keepAspectRatio(false)
		       	 		  .toFiles(new File(targetImagePath + "thumbnails\\"), 
	       	 				   Rename.PREFIX_HYPHEN_THUMBNAIL);
				
			} catch (IOException e) {
				log.error("썸네일 저장 오류 : " + e);
				e.printStackTrace();
			}
			
			
			log.info("---------------------------------------------------------");
			
			// VO 완성
			productVO = new ProductVO();
			productVO.setKind(kind+""); 
			productVO.setName(productName);
			productVO.setImage(encodedFileName);
			productVO.setThumbImage("thumbnail-"+encodedFileName);
			productVO.setContent(productName);
			productVO.setPrice1(price1);
			productVO.setPrice2(price2);
			productVO.setPrice3(price1-price2);
			productVO.setUseyn("y");
			productVO.setBestyn("n");
			
			log.info("ProductVO : " + productVO);
			
			// DB 저장
			if (productService.insertProduct(productVO)==true) {
				log.info("상품 저장");
				flag = true;
			} else {
				log.info("상품 저장 실패");
				flag = true;
			} // 
			
		} // for
		
		return flag;
	}

}
