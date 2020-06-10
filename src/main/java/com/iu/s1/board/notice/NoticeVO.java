package com.iu.s1.board.notice;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.iu.s1.board.BoardVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "notice")
public class NoticeVO extends BoardVO{
	
	//앞이 자기자신
	//noticeVO는 하나이기 때문에 one / to / fileVO는 list이기 때문에 Many
	@OneToMany(mappedBy = "noticeVO", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	private List<NoticeFileVO> noticeFileVOs;
	
	//fetch = FetchType.EAGER : NoticeVO를 조회하면 FileVO까지 다 가져옴
	//fetch = FetchType.LAZY : NoticeVO를 조회하면 일단 자기자신만 가져오는데 FileVO사용하면 그 때 가져옴
	
}
