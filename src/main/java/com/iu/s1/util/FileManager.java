package com.iu.s1.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	public String saveFileCopy(MultipartFile file, File dest) throws Exception{
		String fileName = "";
		fileName = UUID.randomUUID().toString();
		
		fileName = fileName + "_" + file.getOriginalFilename();
		
						//이 폴더에  //이 이름으로 저장
		dest = new File(dest, fileName);
		
		FileCopyUtils.copy(file.getBytes(), dest);
		
		return fileName;
	}
	
	public String saveTransfer(MultipartFile file, File dest) throws Exception{
		String fileName = "";
		fileName = UUID.randomUUID().toString();
		
		fileName = fileName + "_" + file.getOriginalFilename();
		
						//이 폴더에  //이 이름으로 저장
		dest = new File(dest, fileName);
		
		file.transferTo(dest);
		
		return fileName;
	}
	
}
