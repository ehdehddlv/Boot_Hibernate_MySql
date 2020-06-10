package com.iu.s1.board.notice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s1.util.Pager;

@Controller
@RequestMapping("/notice/**/")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("noticeWrite")
	public ModelAndView boardInsert() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("path", "Write");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("noticeWrite")
	public ModelAndView boardInsert(NoticeVO noticeVO, MultipartFile[] files) throws Exception{
		ModelAndView mv = new ModelAndView();
		noticeVO = noticeService.boardInsert(noticeVO, files);
		mv.addObject("path", "Write");
		mv.setViewName("redirect:./noticeList");
		return mv;
	}
	
	@GetMapping("noticeList")
	//@PageableDefault(page = 0, size = 10, direction = Direction.DESC, sort = {"num"}) Pageable pageable, @RequestParam(defaultValue = "") String search
	public ModelAndView boardList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
			
		//									(Page, Size, Sort, column)
		//Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "num"); 
		List<NoticeVO> ar = noticeService.boardList(pager);
		
		mv.addObject("list", ar);
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@GetMapping("noticeSelect")
	public ModelAndView boardSelect(long num, NoticeVO noticeVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		Optional<NoticeVO> opt = noticeService.boardSelect(num);
		noticeVO = opt.get();
		mv.addObject("vo", noticeVO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
}
