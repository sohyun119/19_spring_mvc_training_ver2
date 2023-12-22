<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<body>
	
	<div align="center">
	
		<img src="/img/member.PNG"/>
		
		<c:choose>
			<c:when test="${sessionScope.memberId eq null}">
				<p><a href="/member/register">회원가입</a></p>
				<p><a href="/member/login">로그인</a></p>	
			</c:when>
			<c:otherwise>
				<p><a href="/member/modify">수정하기</a></p>	
				<p><a href="/member/remove">탈퇴하기</a></p>	
				<p><a href="/member/logout">로그아웃</a></p>
			</c:otherwise>
		</c:choose>
		
	</div>

</body>
</html>