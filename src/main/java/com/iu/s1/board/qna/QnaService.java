package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnaService {

	@Autowired
	private QnaRepository qnaRepository;
	
	public QnaVO boardInsert(QnaVO qnaVO) throws Exception{
		//원본글
		//ref = 자기자신의 글번호
		//step, depth = 0
		//ref에 자기 자신의 num을 넣어주고 update하고 리턴
		qnaVO = qnaRepository.save(qnaVO);
		qnaVO.setRef(qnaVO.getNum());
		return qnaRepository.save(qnaVO);
	}
	
	public void boardList() throws Exception{
		
	}
}
