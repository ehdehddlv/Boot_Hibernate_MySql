package com.iu.s1.board.notice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.board.BoardVO;
import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;
import com.iu.s1.util.Pager;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private FilePathGenerator filePathGenerator;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${board.notice.filePath}")
	private String filePath;
	
	public NoticeVO boardInsert(NoticeVO noticeVO, MultipartFile[] files) throws Exception{
		
		File file = filePathGenerator.getUseClassPathResource(filePath);
		
		noticeVO = noticeRepository.save(noticeVO);
		
		List<NoticeFileVO> noticeFileVOs = new ArrayList<>();
		
		for(MultipartFile mf : files) {
			if(mf.getSize()<=0) {
				continue;
			}
			String fileName = fileManager.saveFileCopy(mf, file);
			NoticeFileVO noticeFileVO = new NoticeFileVO();
			noticeFileVO.setFileNum(noticeVO.getNum());	//보류
			noticeFileVO.setNoticeVO(noticeVO);
			noticeFileVO.setFileName(fileName);
			noticeFileVO.setOriName(mf.getOriginalFilename());
			noticeFileVOs.add(noticeFileVO);
			
			noticeVO.setBoardFileVOs(noticeFileVOs);
			
			//System.out.println(fileName);
		}
		
		return noticeVO; 
	}
	
	public Page<NoticeVO> boardList(Pager pager) throws Exception{
		pager.makeRow();
		//pager.makePage(noticeRepository.countByTitleContaining(pager.getSearch()));
		Pageable pageable = PageRequest.of(pager.getStartRow(), pager.getSize(), Sort.Direction.DESC, "num");
		
		Page<NoticeVO> noa = null;
		
		if(pager.getKind().equals("writer")) {
			noa = noticeRepository.findByWriterContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("contents")) {
			noa = noticeRepository.findByContentsContaining(pager.getSearch(), pageable);
		} else {
			noa = noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
		}
		
		pager.makePage(noa.getTotalPages());
		
		return noa;
	}
	
	public NoticeVO boardSelect(NoticeVO noticeVO) throws Exception{
		noticeVO = noticeRepository.findById(noticeVO.getNum()).get();
		noticeVO.setHit(noticeVO.getHit()+1);
		return noticeRepository.save(noticeVO);
	}
}
