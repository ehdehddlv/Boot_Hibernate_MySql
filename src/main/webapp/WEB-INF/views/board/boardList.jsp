<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
	<c:import url="../template/nav.jsp"></c:import>
	<div class="container">
		<h2>${board} List</h2>
		 
		<form id="frm" class="col-xs-6" action="./${board}List">
			<input type="hidden" name="page" id="p">
			    <div class="input-group">
			    
			    <select class="form-control" id="sel1" name="kind">
				    <option id="title" value="title">Title</option>
				    <option id="writer" value="writer">Writer</option>
				    <option id="contents" value="contents">Contents</option>
				</select>
			    
			      <input type="text" class="form-control" placeholder="Search" name="search" value="${param.search}">
			      <div class="input-group-btn">
			        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
			      </div>
			    </div>
		  	</form>
		
		<table class="table table-hover">
			<tr>
				<td>Num</td>
				<td>Title</td>
				<td>Writer</td>
				<td>Date</td>
				<td>Hit</td>
			</tr>
			
			<c:forEach items="${page.content}" var="vo">
				<tr>
					<td>${vo.num}</td>
					<td>
						<c:catch>
						<c:forEach begin="1" end="${vo.depth}">
							--
							<!-- &nbsp;&nbsp; -->
						</c:forEach>
						</c:catch>
						
					<a href="${board}Select?num=${vo.num}">${vo.title}</a></td>
					<td>${vo.writer}</td>
					<td>${vo.regDate}</td>
					<td>${vo.hit}</td>
				</tr>
			</c:forEach>
		</table>

		<%-- <div>
			<c:forEach begin="1" end="${page.totalPages}" var="i">
				${i}
			</c:forEach>
		</div> --%>
		
		<div>
			
			<span><a href="#" class="custompager" title="0">&lt;&lt;</a></span>
			<span><a href="#" class="custompager" title="${page.number-1}">&lt;</a></span> 
			
			<c:forEach begin="${page.number}" end="${page.number+4}" var="i">
				<c:if test="${i lt page.totalPages}">
					<a href="#" class="custompager" title="${i}">${i+1}</a>
				</c:if>
			</c:forEach>
			
			<span><a href="#" class="custompager" title="${page.number+1}">&gt;</a></span>
			<span><a href="#" class="custompager" title="${page.totalPages-1}">&gt;&gt;</a></span>
			
		</div>
		
		<a href="./${board}Write" class="btn btn-danger">Write</a>
		
		<%-- <c:if test="${member.id eq 'admin'}">
			<a href="./${board}Write" class="btn btn-danger">Write</a>
		</c:if>
		<c:if test="${member ne null && member.id ne 'admin'}">
			<a href="./${board}Write" class="btn btn-primary">Write</a>
		</c:if> --%>
	</div>
	
	<script type="text/javascript">

		//페이징처리
		$(".custompager").click(function(){
			var page = $(this).attr("title");
			$("#p").val(page);
			$("#frm").submit();
			
		});

		//검색창이 피어있으면 default값을 title로 맞춤
		var kind = '${param.kind}';
		if(kind == ''){
			$("#title").prop("selected", true);
		}else{
			$("#"+kind).prop("selected", true);
		}
		


	
		var result = '${result}';
		if(result != ''){
			if(result=='1'){
				alert('Write Success');
			}else{
				alert('Write Fail');
			}
		}
	</script>
	
</body>
</html>