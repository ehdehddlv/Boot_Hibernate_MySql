package com.iu.s1.board;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //Default 생성자
@AllArgsConstructor //생성자
@MappedSuperclass	//테이블을 생성하지않고 부모 역할만 해줌
public class BoardVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long num;
	@Column
	private String title;
	@Column
	private String writer;
	@Column
	private String contents;
	@Column
	@CreationTimestamp	//자동으로 오늘 날짜를 넣어줌
	//@UpdateTimestamp	//업데이트 될 때에도 날짜를 넣어줌
	private Date regDate;
	@Column
	private long hit;
	
}
