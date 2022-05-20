package com.javateam.project.util;

public class SearchWordTest {

	public static void main(String[] args) {
		
		// String str = "  심    당  빌딩    ";
		String str = "  심당빌딩       404호  "; // 심당빌딩  404호
		// str = str.trim().replaceAll("  ", " ");
		str = str.trim().replaceAll("[ ]{2,}", " ");
		
		System.out.println("str :"+str + ":end");
	}

}
