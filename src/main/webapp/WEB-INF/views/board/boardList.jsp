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
		 
		<form class="col-xs-6" action="./${board}List">
			    <div class="input-group">
			    
			    <select class="form-control" id="sel1" name="kind">
				    <option value="bt">Title</option>
				    <option value="bc">Contents</option>
				    <option value="bw">Writer</option>
				</select>
			    
			      <input type="text" class="form-control" placeholder="Search" name="search">
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
			
			
			
			<c:if test="${not page.isFirst()}">
				<span><a href="./${board}List?page=0">&lt;&lt;</a></span>
				<span><a href="./${board}List?page=${page.number-1}">&lt;</a></span> 
			</c:if>
			
			<c:forEach begin="${page.number}" end="${page.number+4}" var="i">
				<c:if test="${i lt page.totalPages}">
					<a href="./${board}List?page=${i}">${i+1}</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${not page.isLast()}">
				<span><a href="./${board}List?page=${page.number+1}">&gt;</a></span>
				<span><a href="./${board}List?page=${page.totalPages-1}">&gt;&gt;</a></span>
			</c:if>
			<hr>
			<c:if test="${not page.isFirst()}">
				<a href="./${board}List?page=${page.number-1}">[이전페이지]</a> 
			</c:if>
			<%-- <span>${param.page}</span> --%>
			<span>${page.number+1}</span>
			<c:if test="${not page.isLast()}">
				<a href="./${board}List?page=${page.number+1}">[다음페이지]</a>
			</c:if>
		</div>

		<%-- <div>
			<ul class="pagination">
				<c:if test="${pager.curBlock gt 1}">
					<li><a
						href="./${board}List?curPage=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">이전</a></li>
				</c:if>
				<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
					<li><a
						href="./${board}List?curPage=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
				</c:forEach>
				<c:if test="${pager.curBlock lt pager.totalBlock}">
					<li><a
						href="./${board}List?curPage=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">다음</a></li>
				</c:if>
			</ul>	
		</div> --%>
		
		<a href="./${board}Write" class="btn btn-danger">Write</a>
		
		<%-- <c:if test="${member.id eq 'admin'}">
			<a href="./${board}Write" class="btn btn-danger">Write</a>
		</c:if>
		<c:if test="${member ne null && member.id ne 'admin'}">
			<a href="./${board}Write" class="btn btn-primary">Write</a>
		</c:if> --%>
	</div>
	
	<script type="text/javascript">
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