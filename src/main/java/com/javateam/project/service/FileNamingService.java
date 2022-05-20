package com.javateam.project.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileNamingService {

	/**
	 * 실제 저장 파일명 암호화 처리(UUID)<br> 
	 * 효과) 업로드 파일들의 중복 방지<br>
	 *  
	 * @param fileName 원본 파일명 ex) abcd.pdf 
	 * @return 업로드할 파일명 ex) abcd_암호화코드(UUID).pdf
	 */
	public final String enFilenameUUID(String fileName) {
	
		// 파일명 인코딩(암호화) : UUID
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString() + "_" + fileName;
	} //
	
	
	/**
	 * 실제 저장 파일명으로 복호화 처리(원본 파일 추출)<br>
	 * ex) 118d9e17-af23-4004-a105-601512866fbe_jquery-3.6.0.js ==> jquery-3.6.0.js <br>
	 * 
	 * @param encodedFilename 암호화 처리된 파일명 ex) UUID코드_abcd.pdf
	 * @return 복호화된 원래 파일명 ex) abcd.pdf 
	 */
	public final String decodeFilenameUUID(String encodedFilename) {
		
		int lastIndex = encodedFilename.indexOf("_"); 
		String originalFilename = encodedFilename.substring(lastIndex+1);
		
		log.info("추출된 파일명 : " + originalFilename);
		
		return originalFilename;
	} //
	
}
