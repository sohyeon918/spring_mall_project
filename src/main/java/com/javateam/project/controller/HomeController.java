package com.javateam.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javateam.project.service.ProductService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {
	
	@Autowired
	ProductService pSvc;
	
	@RequestMapping("/")
	public String home() {
		
		log.info("home");
		
		return "redirect:index";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		
		log.info("index");

		request.setAttribute("bestProductList", pSvc.listBestProduct());
		model.addAttribute("newProductList", pSvc.listNewProduct());
		
		// pSvc.listBestProduct().forEach(x->{log.info(x+"\n");});
		// pSvc.listNewProduct().forEach(x->{log.info(x+"\n");});
		
		return "index";
	}
}

