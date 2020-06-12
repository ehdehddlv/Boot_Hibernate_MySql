package com.iu.s1.board.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {
	//select 
	//findBy컬럼명 조건 ...
	
	public int countByTitleContaining(String search);
	public int countByWriterContaining(String search);
	public int countByContentsContaining(String search);
	
	//select * from notice where title like '%'?'%' order by num desc // pageable이 desc 작업
	public Page<NoticeVO> findByTitleContaining(String search, Pageable pageable);
	
	public Page<NoticeVO> findByWriterContaining(String search, Pageable pageable);
	
	public Page<NoticeVO> findByContentsContaining(String search, Pageable pageable);
	
	
	
	//연습용
	//select * from notice where num>0 order by num desc
	public List<NoticeVO> findByNumGreaterThanOrderByNumDesc(long num);

	//select * from notice where num between 6 and 10 order by num desc
	public List<NoticeVO> findByNumBetweenOrderByNumDesc(long num, long num1);
	
	//select * from notice where title like '%'?'%' order by num desc
	public List<NoticeVO> findByTitleContainingOrderByNumDesc(String title);
	
	
	
}
