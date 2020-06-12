package com.iu.s1.board.qna;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s1.util.Pager;

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
	public ModelAndView boardInsert(QnaVO qnaVO, MultipartFile[] files) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		qnaVO = qnaService.boardInsert(qnaVO, files);
		
		mv.addObject("path", "Write");
		mv.setViewName("redirect:./qnaList");
		
		return mv;
	}
	
	@GetMapping("qnaReply")
	public String boardReply(QnaVO qnaVO, Model model) throws Exception{
		
		model.addAttribute("boardVO", qnaVO);
		model.addAttribute("path", "Reply");
		
		return "board/boardWrite";
	}
	
	@PostMapping("qnaReply")
	public ModelAndView boardReply(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		qnaVO = qnaService.boardReply(qnaVO);
		
		mv.addObject("path", "Reply");
		mv.setViewName("redirect:./qnaList");
		
		return mv;
	}
	
	@GetMapping("qnaUpdate")
	public ModelAndView boardUpdate(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		qnaVO = qnaService.boardSelect(qnaVO);
		
		mv.addObject("boardVO", qnaVO);
		mv.addObject("path", "Update");
		mv.setViewName("board/boardWrite");
		
		return mv;
	}
	
	@PostMapping("qnaUpdate")
	public ModelAndView boardUpdate(ModelAndView mv, QnaVO qnaVO, MultipartFile[] files) throws Exception{
		
		qnaService.boardUpdate(qnaVO, files);
		
		mv.addObject("boardVO", qnaVO);
		mv.addObject("path", "Update");
		mv.setViewName("redirect:./qnaList");
		
		return mv;
	}
	
	
	@GetMapping("qnaList")
	public ModelAndView boardList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//service에서 해도 됌
//		pager.makeRow();
//		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getPerPage(), Sort.by("ref").descending().and(Sort.by("step").ascending()));
		
		Page<QnaVO> ar = qnaService.boardList(pager);
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
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
	@GetMapping("qnaSelect")
	public ModelAndView boardSelect(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.boardSelect(qnaVO);
		
		mv.addObject("vo", qnaVO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	@GetMapping("qnaDelete")
	public ModelAndView boardDelete(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boolean result = qnaService.boardDelete(qnaVO);
		
		if(!result) {
			
		}
		
		mv.setViewName("redirect:./qnaList");
		
		return mv;
	}
}
