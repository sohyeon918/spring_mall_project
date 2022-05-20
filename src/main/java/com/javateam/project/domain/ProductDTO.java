package com.javateam.project.domain;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//

@Getter
@Setter
// @Data
public class ProductDTO {
	
	private int pseq;
	private String name;
	private String kind;
	private int price1;
	private int price2;
	private int price3;
	private String content;
	
	private MultipartFile image;
	private String thumbImage;
	
	private String useyn;
	private String bestyn;
	private Timestamp indate;
	
	public ProductDTO() {}
	
	// VO -> DTO
	public ProductDTO(ProductVO productVO) {
		
		this.pseq = productVO.getPseq();
		this.name = productVO.getName();
		this.kind = productVO.getKind();
		this.price1 = productVO.getPrice1();
		this.price2 = productVO.getPrice2();
		this.price3 = productVO.getPrice3();
		this.content = productVO.getContent();
		// this.image = productVO.getImage();
		// this.thumbImage = productVO.getThumbImage();
		this.useyn = productVO.getUseyn() == null ? "y" : productVO.getUseyn();
		this.bestyn = productVO.getBestyn() == null ? "n" : productVO.getBestyn();
		// this.indate = productVO.getIndate();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductDTO [pseq=")
			.append(pseq)
			.append(", name=")
			.append(name)
			.append(", kind=")
			.append(kind)
			.append(", price1=")
			.append(price1)
			.append(", price2=")
			.append(price2)
			.append(", price3=")
			.append(price3)
			.append(", content=")
			.append(content)
			.append(", image=")
			.append(image==null ? "" : image.getOriginalFilename()) // 파일명 출력
			.append(", thumbImage=")
			.append(thumbImage)
			.append(", useyn=")
			.append(useyn)
			.append(", bestyn=")
			.append(bestyn)
			.append(", indate=")
			.append(indate)
			.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bestyn == null) ? 0 : bestyn.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((indate == null) ? 0 : indate.hashCode());
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price1;
		result = prime * result + price2;
		result = prime * result + price3;
		result = prime * result + pseq;
		result = prime * result + ((useyn == null) ? 0 : useyn.hashCode());
		return result;
	}

	// 업로드 파일 정보를 제외하고 비교
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProductDTO))
			return false;
		ProductDTO other = (ProductDTO) obj;
		if (bestyn == null) {
			if (other.bestyn != null)
				return false;
		} else if (!bestyn.equals(other.bestyn))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (indate == null) {
			if (other.indate != null)
				return false;
		} else if (!indate.equals(other.indate))
			return false;
		if (kind == null) {
			if (other.kind != null)
				return false;
		} else if (!kind.equals(other.kind))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price1 != other.price1)
			return false;
		if (price2 != other.price2)
			return false;
		if (price3 != other.price3)
			return false;
		if (pseq != other.pseq)
			return false;
		if (useyn == null) {
			if (other.useyn != null)
				return false;
		} else if (!useyn.equals(other.useyn))
			return false;
		return true;
	}
	
}
