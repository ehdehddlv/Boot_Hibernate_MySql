package com.iu.s1.board.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "noticeFile")
public class NoticeFileVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fileNum;
	@Column //(nullable = false)	//notnull
	private String fileName;
	@Column
	private String oriName;
	
	//fileVO는 list이기 때문에 Many / to / noticeVO는 하나이기 때문에 one 
	@ManyToOne
	@JoinColumn(name = "num")
	private NoticeVO noticeVO;
	
	
}
