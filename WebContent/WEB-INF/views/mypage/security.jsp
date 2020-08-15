<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/views/mypage/profile.jsp"></c:import>     
    
<style type="text/css">
.changepw_container{
	margin-top: 262px;
	height: 700px;
	display: inline-block;
	width: 72%;
	text-align: center;
	font-size: 24px;
}

.changepw_container > h1 {	
	margin-bottom: 25px;
	text-align: center;
}
.changepw_container > hr {	
	margin-bottom: 30px;
}
.changepw_container input {	
	width: 350px;
	height: 40px;
	margin-top: 20px;
	margin-left: 18px;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#pwc").blur(function(){
		if( $("#pw").val() != $("#pwc").val() ){
			$("#pwv").text("비밀번호 확인 실패!")
			$("#pwv").css("color", "red")
		}
		if( $("#pw").val() == $("#pwc").val() ){
			$("#pwv").text("비밀번호 확인 성공!")
			$("#pwv").css("color", "blue")
		}
	})
	
	$("#btnConfirm").click(function(){
		if( $("#pw").val() != $("#pwc").val() ){
			$("#pwv").text("비밀번호 확인 실패!")
			$("#pwv").css("color", "red")
			return false;
		}
		if( $("#pw").val() == $("#pwc").val() ){
			$("#pwv").text("비밀번호 확인 성공!")
			$("#pwv").css("color", "blue")
		}
		
	})
})
</script>
<div class="changepw_container" >
<h1>비밀번호 변경</h1>
<hr>
<form action="/security" method="POST">
	<input type="password" name="pw" id="pw" class="form-control" required="required" placeholder="새 비밀번호"/><br>
	<input type="password" name="pwc" id="pwc" class="form-control" required="required" placeholder="새 비밀번호 확인"/><br>
	<input type="submit" id="btnConfirm" value="확인" style="background-color:#ccc;"/>
	<div id="pwv"></div>
</form>
</div>
</section>

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>	
