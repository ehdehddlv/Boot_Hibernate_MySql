package com.iu.s1.util;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Pager {

	private Integer page;		//현재 페이지	(curPage)
	private Integer size;	//한번에 보이는 페이지 (perPage)
	
	private Integer startRow;
	private Integer lastRow;
	
	private Integer totalPage;
	
	private Integer totalBlock;
	private Integer curBlock;
	
	private Integer startNum;
	private Integer lastNum;
	
	private String kind;
	private String search;
	
	//한눈에 보이는 페이지 설정
	public void makeRow() {
		//다음 행으로 넘어갈때 처음으로 보이는 페이지 수  
		this.startRow = this.getPage()-1;	//0, 1, 2가 나와야함
		//다음 행으로 넘어갈때 마지막으로 보이는 페이지 수
		//this.lastRow = this.getCurPage()*this.getPerPage();
	}
	
	//페이지 개수 만들기
	public void makePage(int totalPage) {
		//totalCount = 전체 글의 개수
//		this.totalPage = totalCount/this.getSize();
//		
//		if(totalCount % this.getSize() != 0) {
//			this.totalPage++;
//		}
		
		this.setTotalPage(totalPage);
		
		int perBlock = 5;
		this.totalBlock = totalPage/perBlock;
		
		if(totalPage % perBlock != 0) {
			this.totalBlock++;
		}
		
		this.curBlock = this.page/perBlock;
		
		if(this.page % perBlock != 0) {
			this.curBlock++;
		}
		
		this.startNum = (this.curBlock-1)*perBlock+1;
		this.lastNum = curBlock*perBlock;
		
		if(this.curBlock == this.totalBlock) {
			this.lastNum = this.totalPage;
		}
		
		
	}
	
	
	public Integer getPage() {
		if(this.page==null || this.page==0) {
			this.page = 1;
		}
		return page;
	}
	
	public Integer getSize() {
		if(this.size==null || this.size==0) {
			this.size = 10;
		}
		return size;
	}
	
	public String getKind() {
		if(this.kind == null || this.kind.equals("")) {
			this.kind = "title";
		}
		return kind;
	}
	
	public String getSearch() {
		if(this.search == null) {
			this.search = "";
		}
		return search;
	}
	
	
	
}
