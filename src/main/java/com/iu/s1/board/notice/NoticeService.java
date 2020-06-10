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
			noticeFileVO.setNoticeVO(noticeVO);
			noticeFileVO.setFileName(fileName);
			noticeFileVO.setOriName(mf.getOriginalFilename());
			noticeFileVOs.add(noticeFileVO);
			
			noticeVO.setNoticeFileVOs(noticeFileVOs);
			
			System.out.println(fileName);
		}
		
		return noticeVO; 
	}
	
	public List<NoticeVO> boardList(Pager pager) throws Exception{
		pager.makeRow();
		pager.makePage(noticeRepository.count());
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getPerPage(), Sort.Direction.DESC, "num");
		
		List<NoticeVO> noa = new ArrayList<NoticeVO>();
		
		if(pager.getKind().equals("writer")) {
			//noticeRepository.countByWriterContaining(pager.getSearch());
			noa = noticeRepository.findByWriterContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("contents")) {
			//noticeRepository.countByContentsContaining(pager.getSearch());
			noa = noticeRepository.findByContentsContaining(pager.getSearch(), pageable);
		} else {
			//noticeRepository.countByTitleContaining(pager.getSearch());
			noa = noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
		}
		
		return noa;
	}
	
	public Optional<NoticeVO> boardSelect(long num) throws Exception{
		return noticeRepository.findById(num);
	}
}
