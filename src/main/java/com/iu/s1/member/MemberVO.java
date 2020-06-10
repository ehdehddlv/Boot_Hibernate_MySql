package com.iu.s1.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@Table(name = "member")	//member 라는 테이블과 맵핑 시키는 작업 (create table member)
@DynamicUpdate
public class MemberVO {

	@Id	//primary key 역할
	@NotEmpty
	private String id;
	
	@Column	//일반 컬럼명
	@NotEmpty
	private String pw;
	
	@Transient	//테이블에서 제외
	private String pwCheck;
	
	@Column
	@NotEmpty
	private String name;
	
	@Column
	@NotEmpty
	private String email;
	
	@Column
	@NotEmpty
	private String phone;
	
	//FK 연결
	//mappedBy = "join하는 Entity에 선언된 자기 자신의 Entity 변수명"
	//자기자신 to memberVO
	@OneToOne(mappedBy = "memberVO", cascade = CascadeType.ALL)	//1대1 방식 / 부모가 지워지면 자식도 지워짐
	private MemberFileVO memberFileVO;
	
	
}
