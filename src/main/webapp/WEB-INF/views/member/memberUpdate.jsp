<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.mjfe{
		color:red;	
	}
</style>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
	<c:import url="../template/nav.jsp"></c:import>

			<form:form modelAttribute="memberVO" class="form-horizontal" action="./memberUpdate" method="post">
				<h1>MemberUpdate JPA</h1>
				<div class="form-group">
					<label class="control-label col-sm-2" for="id">ID:</label>
					<div class="col-sm-10">
						<form:input path="id" type="text" class="form-control" placeholder="Enter ID" value="${member.id}" />
						<form:errors path="id" cssClass="mjfe"></form:errors>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="pw">PW:</label>
					<div class="col-sm-10">
						<form:input path="pw" type="password" class="form-control" placeholder="Enter PW" />
						<form:errors path="pw" cssClass="mjfe"></form:errors>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwCheck">PW Check:</label>
					<div class="col-sm-10">
						<form:input path="pwCheck" type="password" class="form-control" placeholder="Enter pwCheck" />
						<form:errors path="pwCheck" cssClass="mjfe"></form:errors>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="name">Name:</label>
					<div class="col-sm-10">
						<form:input path="name" type="text" class="form-control" id="name" placeholder="Enter name" value="${member.name}" />
						<form:errors path="name" cssClass="mjfe"></form:errors>
					</div>
				</div>
				
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="email">Email:</label>
					<div class="col-sm-10">
						<form:input path="email" type="email" class="form-control"  placeholder="Enter Email" value="${member.email}" />
						<form:errors path="email" cssClass="mjfe"></form:errors>
					</div>
				</div>	
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="phone">Phone:</label>
					<div class="col-sm-10">
						<form:input path="phone" type="text" class="form-control"  placeholder="Enter phone" value="${member.phone}" />
						<form:errors path="phone" cssClass="mjfe"></form:errors>
					</div>
				</div>

				<div class="form-group">
					<input type="button" class="btn btn-info" id="add" value="FileAdd">
				</div>
				<div class="form-group" id="f"></div>

			<%-- <input type="hidden" value="${member.memberFileVO.fileNum}" name="memberFileVO.fileNum" class="form-control"> --%>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>

				</div>
			</div>
	</form:form>

<script type="text/javascript">
	$("#add").click(function(){
		$("#f").append('<input type="file" name="files">');
		
	});
</script>

</body>
</html>