package com.iu.s1.member;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;


@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private FilePathGenerator pathGenerator;	//파일의 경로를 만들어 주는 애
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${member.filePath}")	//value 값을 filePath에 넣음(application.properties)
	private String filePath;
	
	public MemberVO insert(MemberVO memberVO) throws Exception{
		return memberRepository.save(memberVO); 
	}
	
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean check = false;
		
		if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
			bindingResult.rejectValue("pwCheck", "memberVO.password.notSame");
			check = true;
		}
		
		if(memberRepository.existsById(memberVO.getId())) {
			bindingResult.rejectValue("id", "memberVO.iD.value");
			check = true;
		}
		 
		
		
		return check;
		
	}
	
	public MemberVO memberLogin(String id, String pw) throws Exception{
		return memberRepository.findByIdAndPw(id, pw);
	}
	
	public Optional<MemberVO> memberSelect(String id) throws Exception{
		return memberRepository.findById(id);
	}
	
	public MemberVO memberUpdate(MemberVO memberVO, MultipartFile multipartFile) throws Exception{
		File file = pathGenerator.getUseClassPathResource(filePath);
		//File file = pathGenerator.getUseServletContext(filePath);
		
		memberVO = memberRepository.save(memberVO);
		
		String fileName = fileManager.saveFileCopy(multipartFile, file);
		MemberFileVO memberFileVO = new MemberFileVO();
		memberFileVO.setFileName(fileName);
		memberFileVO.setOriName(multipartFile.getOriginalFilename());
		memberFileVO.setMemberVO(memberVO);
		
		memberVO.setMemberFileVO(memberFileVO);
		
		return memberVO;
	}
	
}
