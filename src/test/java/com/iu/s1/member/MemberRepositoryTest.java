package com.iu.s1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	//@Test
	void loginTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu");
		memberVO.setPw("iu");
		memberVO = memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
		System.out.println(memberVO.getMemberFileVO().getFileName());
		System.out.println(memberVO.getMemberFileVO().getOriName());
		assertNotNull(memberVO);
		
	}
	
	//@Test
	void idCheck() {
		MemberVO memberVO = new MemberVO();
		boolean check = memberRepository.existsById("iu");
		System.out.println(check);
	}
	
	//@Test
	void insertTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("choa");
		memberVO.setPw("choa");
		memberVO.setName("choa");
		memberVO.setEmail("choa@sjh");
		memberVO.setPhone("01044445555");
		
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("fileName");
		memberFileVO.setOriName("oriName");
		
		memberVO.setMemberFileVO(memberFileVO);
		
		memberRepository.save(memberVO);
		//Member Insert 성공
		//MemberFile에 ID가 NULL이 들어감
		
//		List<MemberVO> ar = new ArrayList<>();
//		ar.add(memberVO);
//		
//		ar = memberRepository.saveAll(ar);
		
		assertNotNull(memberVO);
		
	}
	
	//@Test
	public void insertTest2() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu");
		memberVO.setPw("iu");
		memberVO.setName("iu");
		memberVO.setEmail("iu@sjh");
		memberVO.setPhone("01022223333");
		
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName("fileName");
		memberFileVO.setOriName("oriName");
		
		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);
		//다시 MemberFile에 Member의 ID를 주입 (서로 주입시켜줌)
		
		memberRepository.save(memberVO);
	}
	
	@Test
	void updateTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu");
		memberVO.setEmail("iu@dda.com");
		memberVO.setName("iuiu");
		memberVO.setPhone("01011111111");
		memberVO.setPw("iu");
		
//		MemberFileVO memberFileVO = new MemberFileVO();
//		memberFileVO.setFileName("change File Name");
//		memberFileVO.setOriName("change Ori Name");
//		memberFileVO.setFileNum(5);
//		
//		memberVO.setMemberFileVO(memberFileVO);
//		memberFileVO.setMemberVO(memberVO);
		
		memberVO = memberRepository.save(memberVO);
		
		assertNotNull(memberVO);
	}
	
	//@Test
	void deleteTest() {
		memberRepository.deleteById("iu");
		
		
		
		
		//suzy, choa
//		MemberVO memberVO = new MemberVO();
//		memberVO.setId("choa");
//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setId("suzy");
//		
//		List<MemberVO> ar = new ArrayList<>();
//		ar.add(memberVO);
//		ar.add(memberVO2);
//		memberRepository.deleteAll(ar);
		
	}

}
