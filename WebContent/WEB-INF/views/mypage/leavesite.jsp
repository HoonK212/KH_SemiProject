<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/mypage/profile.jsp"></c:import>

<style type="text/css">

.leavesite {
	margin-top: 262px;
	height: 700px;
	display: inline-block;
	width: 72%;
	text-align: center;
	font-size: 24px;
}

.leavesite h1 {
	margin-bottom: 25px;
	text-align: center;
}

.leavesite hr {
	margin-bottom: 30px;
}
.leavesite button {	
	width: 350px;
	height: 40px;
	margin-top: 20px;
	margin-left: 6px;
}
</style>

<div class="leavesite">
	<h1>사이트 탈퇴</h1>
	<hr><br>
	
	<h1>정말 탈퇴하시겠습니까?</h1>
	<form action="/leavesite" method="POST">
		<button>네, 탈퇴하겠습니다.</button><br>
		<button type="button" onclick="location.href='/editProfile'">아니요, 계속하겠습니다.</button>
	</form>

</div>

</section>

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>