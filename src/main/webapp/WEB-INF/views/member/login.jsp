<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script src="/jquery/jquery-3.6.1.min.js"></script>
<script>

	$(function(){
		
		$("#loginBtn").click(function(){
			
			let loginData = {
				"memberId" : $("[name='memberId']").val(),
				"passwd" : $("[name='passwd']").val(),
			}
			
			$.ajax({
				url : "/member/login",
				type : "post",
				data : loginData,
				success : function(isValidMember) {
					
					if (isValidMember == "y") {
						location.href = "/member/main";
					}
					else {
						$("#failMsg").html("<span style='color:red;'>아이디와 패스워드를 확인하세요.</span>");
					}
					
				}
				
				
			});
			
		});
		
	});

</script>
</head>
<body>

	<div align="center">
	<h3>Login</h3>
		<table border="1">
			<tr>
				<td align="center">아이디</td>
				<td><input type="text"  name="memberId" placeholder="아이디를 입력하세요." /></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="passwd" placeholder="패스워드를 입력하세요." /> <br>
					<span id="failMsg" ></span>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" id="loginBtn" value="로그인"></td>
			</tr>
		</table>
	</div>
	
</body>
</html>