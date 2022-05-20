package com.javateam.project.domain;

import lombok.Data;

@Data
public class SearchVO {

	/** 검색 키워드 ex) 이름 */
	private String searchFld;
	
	/** 동등/유사 검색 여부 ex) 유사(like),  동등(=) */
	private String isLike;
	
	/** 검색어 */
	private String searchWord;
	
	/** 페이지 */
	private int page;
	
	/** 페이지당 레코드 수 */
	private int limit;
	
}
