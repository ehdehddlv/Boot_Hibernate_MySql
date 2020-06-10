package com.iu.s1.member;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.PastOrPresent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**/")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	
	@GetMapping("memberJoin")
	public ModelAndView insert() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberVO", new MemberVO());
		mv.setViewName("member/memberJoin");
		return mv;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView insert(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mv.setViewName("member/memberJoin");
		}else {
			boolean resultError = memberService.memberError(memberVO, bindingResult);
			
			if(resultError) {
				mv.setViewName("member/memberJoin");
			}else {
				memberVO = memberService.insert(memberVO);
				mv.setViewName("redirect:../");
			}
		}
		
		return mv;
	}
	
	@GetMapping("memberLogin")
	public void memberLogin() throws Exception{
		
	}
	
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		String id = memberVO.getId();
		String pw = memberVO.getPw();
		memberVO = memberService.memberLogin(id, pw);

		if(memberVO != null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}
		
		return mv;
	}
	
	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
	@GetMapping("memberPage")
	public ModelAndView memberSelect(MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
//		String id = memberVO.getId();
//		String pw = memberVO.getPw();
//		memberVO = memberService.memberLogin(id, pw);
//		mv.addObject("member", memberVO);
		mv.setViewName("member/memberSelect");
		return mv;
	}
	
	
	@GetMapping("memberUpdate")
	public String memberUpdate(Model model) throws Exception{
		model.addAttribute("memberVO", new MemberVO());
		return "member/memberUpdate";
	}
	
	@PostMapping("memberUpdate")
	public ModelAndView memberUpdate(@Valid MemberVO memberVO, BindingResult bindingResult, MultipartFile multipartFile) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.memberUpdate(memberVO, multipartFile);
		
		
		mv.addObject("member", memberVO);
		mv.setViewName("redirect:./memberPage");
		return mv;
	}
	
}
