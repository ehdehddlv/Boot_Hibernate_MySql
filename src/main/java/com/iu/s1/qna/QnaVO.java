package com.iu.s1.qna;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.iu.s1.board.BoardVO;

import lombok.Data;

@Data
@Entity
@Table(name = "qna")
public class QnaVO extends BoardVO{

	private long ref;
	private long step;
	private long depth;
	
}
