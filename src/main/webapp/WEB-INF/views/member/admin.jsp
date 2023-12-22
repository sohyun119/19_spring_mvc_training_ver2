<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>admin</title>
<script src="/jquery/jquery-3.6.1.min.js"></script>
<script>

	$().ready(function() {
		
		$("[name='searchWord']").keyup(function(){
			
			var param = {
				"searchKeyword" : $("[name='searchKeyword']").val(),
				"searchWord" : $("[name='searchWord']").val()
			}
		   
		    $.ajax({
		       type : "get",
		       url : "/member/searchMemberList",
		       data : param,
		       success : function(data){
		          
		    	   var memberList = "";
		    	   if (data.length == 0) {
		    		   memberList += "<tr align='center'>";
		    		   memberList += "<td colspan='7'><strong>조회된 회원이 없습니다.</strong></td>";
	    			   memberList += "</tr>";
		    	   }
		    	   else {
		    		   
			    	   $(data).each(function(){
			    		   
			    		   memberList += "<tr>";
			    		   memberList += "<td><img src='/member/thumbnails?fileName=" + this.profileUUID +"' width='50' height='50' alt='사진'></td>";
			    		   memberList += "<td>"+ this.memberId + "</td>";
			    		   memberList += "<td>"+ this.memberNm + "</td>";
			    		   memberList += "<td>"+ this.hp + "</td>";
			    		   memberList += "<td>";
			    		   memberList += this.roadAddress +"<br>"; 
			    		   memberList += this.jibunAddress +"<br>";
			    		   memberList += this.namujiAddress +"<br>";
			    		   memberList += "</td>";
			    		   
			    		   let joinDt = new Date(this.joinAt);
			    		   let year = joinDt.getFullYear();
			    		   let month = joinDt.getMonth() + 1;	 if (month < 10) month = "0" + month;
			    		   let date = joinDt.getDate();			 if (date < 10) date = "0" + date;
			    		   
			    		   memberList += "<td>"+ year + "-" + month + "-" + date + "</td>";
			    		   memberList += "<td>"+ this.activeYn + "</td>";
			    		   memberList += "</tr>";
			    		   
			    	   });
		    		   
		    	   }
		    	   
		    	   
		    	  $("#memberList").html(memberList);
		    	   
		       }
		       
		    });
			
		});	
		
		$("#execelExport").click(function(){
			
			var url = "/member/excelExport";
				url += "?searchKeyword=" + $("[name='searchKeyword']").val();
				url += "&searchWord=" + $("[name='searchWord']").val();
			
			location.href = url;
	
		});	
		
		
	});
	
</script>
</head>
<body>
	
	<div align="center">
	
		<h3>회원 리스트</h3>
		<table border="1" style="align-content: center; width: 700;">
				<tr>
					<td colspan="7" align="right">
						<input type="button" value="Excel Export" id="execelExport" />
					</td>
				</tr>
				<tr>
					<td>프로필</td>
					<td>회원아이디</td>
					<td>회원이름</td>
					<td>휴대폰번호</td>
					<td>주소</td>
					<td>가입일</td>
					<td>활성화()</td>
				</tr>
				<tbody id="memberList">
					<c:choose>
						<c:when test="${empty memberList}">
							<tr align="center">
								<td colspan="7"><strong>조회된 회원이 없습니다.</strong></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="memberDTO" items="${memberList}">
								<tr>
									<td><img src="/member/thumbnails?fileName=${memberDTO.profileUUID}" width="50" height="50" alt="사진"></td>
									<td>${memberDTO.memberId}</td>
									<td>${memberDTO.memberNm}</td>
									<td>${memberDTO.hp}</td>
									<td>
										${memberDTO.roadAddress}<br> 
										${memberDTO.jibunAddress}<br>
										${memberDTO.namujiAddress}<br>
									</td>
									<td><fmt:formatDate value="${memberDTO.joinAt}" pattern="yyyy-MM-dd" /></td>
									<td>${memberDTO.activeYn}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
				<tr>
					<td colspan="7" align="center">
						<select name="searchKeyword">
							<option value="memberId">회원아이디</option>
							<option value="memberNm">회원이름</option>
							<option value="avtiveYn">활성화</option>
							<option value="address">주소</option>
						</select>
						<input type="text" name="searchWord" />
					</td>
				</tr>
		</table>
		
	</div>
	
	
</body>
</html>

