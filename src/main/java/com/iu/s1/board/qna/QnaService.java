package com.iu.s1.board.qna;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iu.s1.util.Pager;

@Service
@Transactional(rollbackOn = Exception.class)
public class QnaService {

	@Autowired
	private QnaRepository qnaRepository;
	
	public QnaVO boardInsert(QnaVO qnaVO) throws Exception{
		//원본글
		//ref = 자기자신의 글번호
		//step, depth = 0
		//ref에 자기 자신의 num을 넣어주고 update하고 리턴
		qnaVO.setStep(0L);
		qnaVO.setDepth(0L);
		qnaVO = qnaRepository.save(qnaVO);
		qnaVO.setRef(qnaVO.getNum());
		return qnaRepository.save(qnaVO);
	}
	
	public Page<QnaVO> boardList(Pager pager) throws Exception{

		pager.makeRow();
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getPerPage(), Sort.by("ref").descending().and(Sort.by("step").ascending()));
		
		Page<QnaVO> ar = null;
		
		if(pager.getKind().equals("title")) {
			ar = qnaRepository.findByTitleContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("writer")) {
			ar = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);
		}else {
			ar = qnaRepository.findByContentsContaining(pager.getSearch(), pageable);
		}
		
		return ar;
	}
}
