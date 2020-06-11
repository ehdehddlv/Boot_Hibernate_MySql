package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/qna/**/")
public class QnaContoller {

	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna"; 
	}
	
	@GetMapping("qnaWrite")
	public ModelAndView boardInsert() throws Exception{
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("boardVO", new QnaVO());
		mv.addObject("path", "Write");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaWrite")
	public ModelAndView boardInsert(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		qnaVO = qnaService.boardInsert(qnaVO);
		
		mv.addObject("path", "Write");
		mv.setViewName("redirect:./qnaList");
		
		return mv;
	}
	
//	@GetMapping("qnaList")
//	public ModelAndView boardList() throws Exception{
//		ModelAndView mv = new ModelAndView();
//		List<QnaVO> ar = qnaService.boardList();
//		mv.addObject("list", ar);
//		mv.setViewName("board/boardList");
//		return mv;
//	}
	
	
}
