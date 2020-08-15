<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!-- header -->
<c:import url="/WEB-INF/views/login/header.jsp"></c:import>

<script type="text/javascript">
$(document).ready(function(){
	$("#btnChange").click(function(){
		//필수입력
		if( $("#pw").val() == null || $("#pw").val() == "" ){
			$("#pwv").text("새 비밀번호를 입력하세요.")
			$("#pwv").css("color", "red")
			return false;
		}
		if( $("#pwc").val() == null || $("#pwc").val() == "" ){
			$("#pwcv").text("비밀번호 재확인 필수입니다.")
			$("#pwcv").css("color", "red")
			return false;
		} else if( $("#pw").val() != $("#pwc").val() ) {
			$("#pwcv").text("비밀번호가 일치하지 않습니다.")
			$("#pwcv").css("color", "red")
			return false;
		}
	})
	$("#pwc").blur(function(){
		if( $("#pw").val() != $("#pwc").val() ) {
			$("#pwcv").text("비밀번호가 일치하지 않습니다.")
			$("#pwcv").css("color", "red")
			return false;
		}
	})
});
</script>

<div style="width: 500px; margin:0 auto; height: 700px;">
<h1 style="text-align: center">비밀번호 재설정</h1>
<hr>
<div><h3 style="text-align: center">아이디: ${id }</h3></div>
<form action="/changepw" method="POST">
	<div class="form-group" style="text-align: center">
		<input type="password" id="pw" name="pw" class="form-control" style="width: 450px; display:inline-block" required="required" placeholder="새 비밀번호" />
		<div id="pw"></div>
	</div>
	
	<div class="form-group" style="text-align: center">
		<input type="password" id="pwc" name="pwc" class="form-control" style="width: 450px; display:inline-block" required="required" placeholder="새 비밀번호 확인"/>
	<div id="pwcv" style="text-align: center"></div>
	</div>
	
	<div class="form-group" style="width: 120px; margin:0 auto;">
		<input id="btnChange" type="submit" class="form-control" value="확인" style="width: 100px; background: #2AC1BC; color: white;"/>
	</div>
</form>
</div>

<c:import url="/WEB-INF/views/login/footer.jsp"></c:import>