package com.javateam.project.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 페이징 객체
 * 
 * @author javateam
 *
 */

@Getter
@Setter
public class PageVO {

	/** 시작 페이지 */
	private int startPage;
	
	/** 현재 페이지 */
	private int currPage;
	
	/** 마지막 페이지 */
	private int endPage;
	
	/** 총 페이지 수 */
	private int maxPage;
	
	/** 페이지당 게시글 수 */
	private int limit;

}