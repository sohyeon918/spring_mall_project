package com.javateam.project.service;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/db-context.xml",
					   "file:src/main/resources/spring/fileUpload-context.xml"})
@Slf4j
public class ShoeCrawlServiceTest {
	
	@Inject
	ShoeCrawlService scs;
	
	@Test
	public void test() throws IOException {
		
		log.info("ShoeCrawlTest");
		
		//for (int i=1; i<=5; i++) {
		// log.info("결과 : " + scs.generateShoeProductsDummy(1));
		// log.info("결과 : " + scs.generateShoeProductsDummy(2));
		// log.info("결과 : " + scs.generateShoeProductsDummy(3));
		// log.info("결과 : " + scs.generateShoeProductsDummy(4));
		log.info("결과 : " + scs.generateShoeProductsDummy(5));
		// }
	} //

}	