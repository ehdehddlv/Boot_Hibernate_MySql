package com.iu.s1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("/message/messageResult")
	public ModelAndView messageConfig(String result, String path) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.addObject("path", path);
		mv.setViewName("common/result");
		return mv;
	}
	
	
	@GetMapping("/")
	public String home() throws Exception{
		return "index";
	}
	
}
