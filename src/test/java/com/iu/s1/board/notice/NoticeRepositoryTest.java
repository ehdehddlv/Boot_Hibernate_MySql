package com.iu.s1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;
	
	private NoticeVO noticeVO;
	
	private List<NoticeFileVO> noticeFileVOs;
	
	@BeforeEach
	public void beforeEach() {
		noticeVO = new NoticeVO();
		noticeVO.setTitle("title");
		noticeVO.setWriter("writer");
		noticeVO.setContents("contents");
		
		List<NoticeFileVO> noticeFileVOs = new ArrayList<NoticeFileVO>();
		
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("fileName");
		noticeFileVO.setOriName("oriName");
		noticeFileVO.setNoticeVO(noticeVO);
		noticeFileVOs.add(noticeFileVO);
		
		noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName("secondFileName");
		noticeFileVO.setOriName("secondOriName");
		noticeFileVO.setNoticeVO(noticeVO);
		noticeFileVOs.add(noticeFileVO);
		
		//noticeVO.setNoticeFileVOs(noticeFileVOs);
		
	}
	
	//insert
	//@Test
	public void insertTest() throws Exception{
		
		for(int i=0; i<100; i++) {
			noticeVO = new NoticeVO();
			noticeVO.setTitle("title"+i);
			noticeVO.setContents("contents"+i);
			noticeVO.setWriter("writer"+i);
			noticeVO = noticeRepository.save(noticeVO);
		}
		
		assertNotNull(noticeVO);
	}
	
	//update
	//@Test
	public void updateTest() throws Exception{
		//noticeVO.setNum(2);
		noticeVO.setTitle("update Title");
		noticeVO = noticeRepository.save(noticeVO);
		assertNotNull(noticeVO);
	}
	
	//delete
	//@Test
	public void deleteTest() throws Exception{
		noticeRepository.deleteById(2L);
		boolean check = noticeRepository.existsById(2L);
		assertEquals(false, check);
	}
	
	//selectList
	//@Test
	public void selectListTest() throws Exception{
		List<NoticeVO> ar = noticeRepository.findAll();
		for(NoticeVO noticeVO : ar) {
			System.out.println(noticeVO.getTitle());
		}
		assertNotEquals(0, ar.size());
	}
	
	//selectOne
	//@Test
	public void selectOneTest() throws Exception{
		Optional<NoticeVO> opt = noticeRepository.findById(3L);
		noticeVO = opt.get();
		//데이터가 있냐 없냐 / opt.isPresent()
		//데이터가 있냐 없냐 / notice != null
		System.out.println(noticeVO.getRegDate());
		//System.out.println(noticeVO.getNoticeFileVOs().get(0).getFileName());
		assertNotNull(noticeVO);
		
	}
	
	@Test
	public void customTest() {
//		List<NoticeVO> ar = noticeRepository.findByNumGreaterThanOrderByNumDesc(0);
//		for(NoticeVO noticeVO : ar) {
//			System.out.println("Num : "+noticeVO.getNum());
//		}
		
//		List<NoticeVO> ar = noticeRepository.findByNumBetweenOrderByNumDesc(6, 10);
//		for(NoticeVO noticeVO : ar) {
//			System.out.println("Num : "+noticeVO.getNum());
//		}
		
		List<NoticeVO> ar = noticeRepository.findByTitleContainingOrderByNumDesc("1");
		for(NoticeVO noticeVO : ar) {
			System.out.println("Title : "+noticeVO.getTitle());
		}
	}
	
}
