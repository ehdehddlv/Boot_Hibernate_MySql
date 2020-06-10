package com.iu.s1.member;

import org.springframework.data.jpa.repository.JpaRepository;

													//멤버VO 선언, VO의 Primary key의 데이터타입
public interface MemberRepository extends JpaRepository<MemberVO, String> {
	//hibernate는 쿼리문을 자동으로 생성하기 때문에 Repository.xml 생성 안해도 됌
	//내장 메서드
	//save() : insert
	//쿼리문 대신 메서드가 제공
	//메서드를 통해서 쿼리문이 자동으로 생성
	// 기본 제공 메서드
	// S save(T)		: insert, update
	// <S extends T> S save(Interable<? extends T>)	: 다중 insert, update
	// void deleteById(ID) : PK를 통한 삭제
	// void delete(T)	: 주어진 Entity delete
	// void deleteAll(Interable<? extends T>) : 주어진 모든 Entity 삭제  
	// void deleteAll() : 모든 Entity 삭제
	// List<T> findAll() : 모든 Entity selectList
	// Optional<T> findById(ID) : PK로 단일 Entity selectOne
	// long count : Entity의 모든 개수
	// boolean existsById(ID) : 해당 PK로 Entity 존재 여부
	// 사용자가 생성하는 메서드 : 쿼리 메서드
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence
	
	//public abstract ... (내가 찾고자 하는 컬럼명)
	//select * from member where id=? and pw=?
	public MemberVO findByIdAndPw(String id, String pw);
	
}
