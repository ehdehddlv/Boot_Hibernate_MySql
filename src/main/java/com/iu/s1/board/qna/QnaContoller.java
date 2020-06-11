package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	
	@GetMapping("qnaList")
	public ModelAndView boardList(@PageableDefault(size = 10, page = 0, direction = Direction.DESC, sort = {"num"}) Pageable pageable) throws Exception{
		ModelAndView mv = new ModelAndView();
		Page<QnaVO> ar = qnaService.boardList(pageable);
		System.out.println(ar.getContent().size());		//현재 보이는 페이지
		System.out.println(ar.getSize());				//한눈에 보이는 개수
		System.out.println("Element : "+ar.getTotalElements());	//총 글 개수
		System.out.println("TotalPages : "+ar.getTotalPages());	//총 페이지 수
		System.out.println("Next : "+ar.hasNext());	//다음 페이지가 있냐?
		System.out.println("Previous : "+ar.hasPrevious());	//이전 페이지가 있냐?
		System.out.println("Number : "+ar.getNumber());	//현재 페이지 숫자
		System.out.println("Content : "+ar.hasContent());	//내용이 있냐?
		System.out.println("First : "+ar.isFirst());	//첫번째 글이냐?
		System.out.println("Last : "+ar.isLast());		//마지막 글이냐?

		mv.addObject("page", ar);
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
	
}
