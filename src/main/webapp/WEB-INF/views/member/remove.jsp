<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>remove</title>
<script src="/jquery/jquery-3.6.1.min.js"></script>
<script>
	$(function(){
		
		$("#confirmDelete").keyup(function(){
			if ("${sessionScope.memberId}/탈퇴" == $(this).val()) {
				$("[type='submit']").prop("disabled" , false);
			}
			else {
				$("[type='submit']").prop("disabled" , true);
			}
		});
		
		
	});
</script>
</head>
<body>

	<div align="center">
	
		<h3>회원탈퇴 </h3>
		<p><span style="color:red;">정말 탈퇴하시겠습니까?</span></p>
		<form action="${contextPath}/member/remove" method="post" >
		
			<p>진한 글씨(<strong>${sessionScope.memberId}/탈퇴</strong>)를 입력하세요.</p>
			<p>
				<input type="text" id="confirmDelete" />
			</p>
			<p>
				<input type="submit" value="탈퇴하기" disabled>
				<input type="button" value="취소" onclick="location.href='boardDetail?boardId=${boardId}'">
			</p>
			
		</form>
	</div>

</body>
</html>