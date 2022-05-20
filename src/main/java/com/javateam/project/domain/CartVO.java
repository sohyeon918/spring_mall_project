package com.javateam.project.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVO {

	private int cseq;
	private String id;
	private int pseq;
	private String mname;
	private String pname;
	private int quantity;
	private int price2;
	private Timestamp indate;
	// 추가
	private int result; // 주문 처리 결과 (1:미처리, 2:처리)

	@Override
	public boolean equals(Object obj) {
		
		int flag = 0;
		CartVO other = (CartVO) obj;
		
		// 동일한 주문자 인지 점검
		if (this.id.equals(other.id)) {
			flag ++;
		}
		
		// 동일한 상품 인지 점검
		if (this.pseq == other.pseq) {
			flag ++;
		}
		
		// 주문 미처리(미완료) 상태인지 점검
		if (this.result == 1 && other.result == 1)  {
			flag++;
		}
		
		return  flag == 3 ? true : false;
	}

}