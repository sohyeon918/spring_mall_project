package com.javateam.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@Log
public class TestController {

	@RequestMapping("/demo")
	public String demo() {
		
		log.info("demo");
		
		return "/test/demo";
	}
	
	@RequestMapping("/bootstrap")
	public String demo2() {
		
		log.info("bootstrap");
		
		return "/test/bootstrap";
	}
	
	@RequestMapping("/bootstrap2")
	public String demo3() {
		
		log.info("bootstrap2");
		
		return "/test/bootstrap2";
	}
	
	@RequestMapping("/slide")
	public String slide() {
		
		log.info("slide");
		
		return "/test/slide";
	}
	
	@RequestMapping("/slide2")
	public String slide2() {
		
		log.info("slide2");
		
		return "/test/slide2";
	}
	
	@RequestMapping("/slide_final")
	public String slideFinal() {
		
		log.info("slideFinal");
		
		return "/test/slide_final";
	}
	
	@RequestMapping("/slide_div")
	public String slideDiv() {
		
		log.info("slideDiv");
		
		return "/test/slide_div";
	}
}
