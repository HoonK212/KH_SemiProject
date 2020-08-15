<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/views/mypage/profile.jsp"></c:import>     
    
<style type="text/css">
.personalInfo_container{
	margin-top: 262px;
	height: 700px;
	display: inline-block;
	width: 72%;
	text-align: center;
	font-size: 24px;
}

.personalInfo_container > h1{
	margin-bottom: 25px;
}

.personalInfo_container > hr {
	margin-bottom: 30px;
}

.personalInfo_container table {
	margin-top: 15px;
	margin-bottom: 30px;
	margin-left: 280px;
	font-size: 20px;
}


.personalInfo_container tr {
	border-bottopm: 1px solid #bcbcbc;
} 


.personalInfo_container th, td {
	padding-top: 12px;
	padding-bottom: 12px;
	padding-right: 70px;
	text-align: left; 
	width: 400px;
} 

</style>
    
	<div class="personalInfo_container">
		<h1>개인 정보</h1>
		<hr>
		<table>
		<tr><th><h3>성명</h3></th><td><h3 style="font-weight: normal;">${username }</h3></td></tr> 
		<tr><th><h3>닉네임</h3></th><td><h3 style="font-weight: normal;">${usernick }</h3></td></tr> 
		<tr><th><h3>성별</h3></th>
		<td>
		<c:choose>
			<c:when test="${usergender eq 'm' }">
			<h3 style="font-weight: normal;"><%="남자" %></h3>
			</c:when>
			<c:when test="${usergender eq 'f' }">
			<h3 style="font-weight: normal;"><%="여자" %></h3>
			</c:when>
		</c:choose> 
		</td>
		</tr>
		<tr><th><h3>생년월일</h3></th><td><h3 style="font-weight: normal;">${userbirth }</h3></td></tr> 
		<tr><th><h3>이메일</h3></th><td><h3 style="font-weight: normal;">${useremail }</h3></td></tr> 
		<tr><th><h3>전화번호</h3></th><td><h3 style="font-weight: normal;">${usertel }</h3></td></tr> 
		<tr><th><h3>관심프랜차이즈</h3></th>
		<td>
			<c:choose>
			<c:when test="${not empty fran2}">
			<h3 style="font-weight: normal;">${fran2 }</h3>
			</c:when>
			<c:when test="${empty fran2 }">
			<h3 style="font-weight: normal;"><%="-" %></h3>
			</c:when>
		</c:choose>
		</td>
		</tr> 
		</table>
	</div>
	
</section>

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>	