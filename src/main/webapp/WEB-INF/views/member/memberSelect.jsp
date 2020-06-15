<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h1>My Page</h1>
		
		<h3>ID : ${member.id}</h3>
		<h3>Name : ${member.name}</h3>
		<h3>Email : ${member.email}</h3>
		<h3>Phone : ${member.phone}</h3>
			
		<c:if test="${member ne null}">
			<a href="memberUpdate?id=${member.id}" class="btn btn-primary">Update</a>
			<a href="memberDelete?id=${member.id}" class="btn btn-danger">Delete</a>
		</c:if>
		
	</div>

</body>
</html>