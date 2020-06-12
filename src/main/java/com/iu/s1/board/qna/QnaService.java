package com.iu.s1.board.qna;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;
import com.iu.s1.util.Pager;

@Service
@Transactional(rollbackOn = Exception.class)
public class QnaService {

	@Autowired
	private QnaRepository qnaRepository;
	@Autowired
	private FilePathGenerator filePathGenerator;
	@Autowired
	private FileManager fileManager;
	@Value("${board.qna.filePath}")
	private String filePath;
	
	public boolean boardDelete(QnaVO qnaVO) throws Exception{
		qnaRepository.deleteById(qnaVO.getNum());
		return qnaRepository.existsById(qnaVO.getNum());
	}
	
	public QnaVO boardInsert(QnaVO qnaVO, MultipartFile[] files) throws Exception{
		//원본글
		//ref = 자기자신의 글번호
		//step, depth = 0
		//ref에 자기 자신의 num을 넣어주고 update하고 리턴
//		qnaVO.setStep(0L);
//		qnaVO.setDepth(0L);
		List<QnaFileVO> list = new ArrayList<>();
		
		//저장할 폴더 경로
		File file = filePathGenerator.getUserResourceLoader(filePath);
		
		//HDD 저장
		for(MultipartFile multipartFile : files) {
			if(multipartFile.getSize()<=0) {
				continue;
			}
			QnaFileVO qnaFileVO = new QnaFileVO();
			String fileName = fileManager.saveFileCopy(multipartFile, file);
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(multipartFile.getOriginalFilename());
			qnaFileVO.setQnaVO(qnaVO);
			list.add(qnaFileVO);
		}
		
		qnaVO.setBoardFileVOs(list);
		
		qnaVO = qnaRepository.save(qnaVO);
		
		qnaVO.setRef(qnaVO.getNum());
		return qnaRepository.save(qnaVO);
	}
	
	public QnaVO boardReply(QnaVO qnaVO) throws Exception{
		QnaVO childVO = new QnaVO();
		childVO.setTitle(qnaVO.getTitle());
		childVO.setWriter(qnaVO.getWriter());
		childVO.setContents(qnaVO.getContents());
		
		//update
		// ref가 부모의 ref 같고 step이 부모의 step보다 큰 것들
		// step 1씩 증가
		// 1. 부모의 정보 조회
		qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		List<QnaVO> ar = qnaRepository.findByRefAndStepGreaterThan(qnaVO.getRef(), qnaVO.getStep());
		
		for(QnaVO q : ar) {
			q.setStep(q.getStep()+1);
		}
		
		//update
		qnaRepository.saveAll(ar);
		
		// 자기자신의 ref는 부모의 ref
		// 자기자신의 step은 부모의 step+1
		// 자기자신의 depth는 부모의 depth+1
		childVO.setRef(qnaVO.getRef());
		childVO.setStep(qnaVO.getStep()+1);
		childVO.setDepth(qnaVO.getDepth()+1);
		
		qnaRepository.save(childVO);
		
		return childVO;
	}
	
	
	public void boardUpdate(QnaVO qnaVO, MultipartFile[] files) throws Exception{
		qnaRepository.qnaUpdate(qnaVO.getTitle(), qnaVO.getContents(), qnaVO.getNum());
	}
	
	
	public Page<QnaVO> boardList(Pager pager) throws Exception{

		pager.makeRow();
		Pageable pageable = PageRequest.of(pager.getStartRow(), pager.getSize(), Sort.by("ref").descending().and(Sort.by("step").ascending()));
		
		Page<QnaVO> ar = null;
		
		if(pager.getKind().equals("title")) {
			ar = qnaRepository.findByTitleContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("writer")) {
			ar = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);
		}else {
			ar = qnaRepository.findByContentsContaining(pager.getSearch(), pageable);
		}
		
		pager.makePage(ar.getTotalPages());
		
		return ar;
	}
	
	public QnaVO boardSelect(QnaVO qnaVO) throws Exception{
		//qnaVO = qnaRepository.findById(qnaVO.getNum()).get();
		qnaVO = qnaRepository.qnaSelect(qnaVO.getNum());
		qnaVO.setHit(qnaVO.getHit()+1);
		return qnaRepository.save(qnaVO);
	}
}
