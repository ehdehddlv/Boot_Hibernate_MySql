package com.iu.s1.board.qna;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QnaRepository extends JpaRepository<QnaVO, Long> {

	@Query("select Q from QnaVO Q where Q.num=:num")
	QnaVO qnaSelect(Long num);
	
	//전체를 가져오는게 아니면 배열로 받음
	//@Query("select Q.title, Q.writer from QnaVO Q where Q.num")
	//Object[] qnaSelect2(Long num);
	 
	//Update JPQL							//1번 ?			//2번 ?			//3번 ?
	//@Transactional
	@Modifying							//순서 상관 있음
	@Query(value = "update QnaVO q set q.title=?1, q.contents=?2 where q.num=?3")
	void qnaUpdate(String title, String contents, Long num);
	
	//Update JPQL							
	// @Transactional
	@Modifying							//순서 맞출 필요 없고, 매개변수명과 일치시키면 됌
	@Query(value = "update QnaVO q set q.title=:title, q.contents=:contents where q.num=:num")
	void qnaUpdate2(String title, String contents, Long num);
	
	//Update JPQL
	// @Transactional
	@Modifying // 정말정말 힘들어서 답이안나오면 native쿼리를 사용하여 원래 쓰던 쿼리문을 사용 (신중하게 생각)
	@Query(nativeQuery = true, value = "update qna set title=:title, contents=:contents where num=:num")
	void qnaUpdate3(String title, String contents, Long num);
	
	
	public Page<QnaVO> findByTitleContaining(String search, Pageable pageable);
	
	public Page<QnaVO> findByWriterContaining(String search, Pageable pageable);
	
	public Page<QnaVO> findByContentsContaining(String search, Pageable pageable);
	
	// ref가 부모의 ref 같고 step이 부모의 step보다 큰 것들
	List<QnaVO> findByRefAndStepGreaterThan(long ref, long step);
	
}
