package com.iu.s1.board.qna;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	
	public Page<QnaVO> boardList(String search, String kind, Pageable pageable) throws Exception{

				
		
		if(kind==null || kind.equals("title")) {
			Page<QnaVO> ar = qnaRepository.findByTitleContaining(search, pageable);
		}else if(kind.equals("writer")) {
			qnaRepository.findByWriterContaining(search, pageable);
		}else {
			
		}
		
		return qnaRepository.findAll(pageable);
	}
}
