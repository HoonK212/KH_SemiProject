<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!-- header -->
<c:import url="/WEB-INF/views/login/header.jsp"></c:import>

<!--XMLHttpRequest 생성  -->
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<script type="text/javascript">
	
	//이름메일 일치변수
	var emailname = false;
	//메일발송 전역변수
	var emailSend = false;
	//코드인증 전역변수
	var codeCheck = false;

$(document).ready(function(){
	
	//메일발송
	$("#btnSendEmail").click(function(){
		
		if( $("#email").val() == "" ){
			$("#emailv").text( "회원가입시 등록한 이메일을 입력하세요." );
			$("#emailv").css( "color", 'red' );
			return false;
		}
		
		var p = "useremail=" + $("#email").val();
		sendRequest("GET", "/send", p, callback1);

	});
	function callback1(){
		console.log("이메일 발송 콜백함수 호출");
		if( httpRequest.readyState == 4 ){
			if( httpRequest.status == 200){
				result1();
			} else console.log("AJAX 요청/응답 에러")
		}
	}
	function result1(){
		var resultvar1 = JSON.parse(httpRequest.responseText);
		
		emailsend = resultvar1.result
		
		if( emailsend == true ){
			$("#emailv").text( "이메일을 발송하였습니다." );
			$("#emailv").css( "color", 'blue' );
		} 
		
		if( emailsend == false ){
			$("#emailv").text( "이메일 발송에 실패했습니다" );
			$("#emailv").css( "color", 'red' );
		} 
	}
	//코드인증
	$("#btnCodeVerify").click(function(){
		console.log("btnCodeVerify");
		var j = "useremail=" + $("#email").val() + "&code=" + $("#code").val();
		console.log(j);
		sendRequest("POST", "/send", j, callback2);
	});
	function callback2(){
		console.log("인증코드 확인 콜백함수 호출");
		if( httpRequest.readyState == 4){
			if( httpRequest.status == 200){
				result2();
			}else console.log("AJAX 요청/응답 에러")
		}
	}
	function result2(){
		var resultvar2 = JSON.parse(httpRequest.responseText);
		
		codeCheck = resultvar2.result;
		
		if( codeCheck == true ){
			$("#codev").text("코드 인증 성공!");
			$("#codev").css("color", 'blue');
			// 아이디 조회
			searchId();
		}
		
		if( codeCheck == false ){
			$("#codev").text("코드 인증 실패!");
			$("#codev").css("color", 'red');
		}
	}
	
	//이름&메일 일치
	function searchId(){
		console.log("search Id...");
		var s = "name=" + $("#name").val() + "&useremail=" + $("#email").val();
		sendRequest("GET", "/searchid", s, callback3);
	}
	
	function callback3(){
		console.log("아이디조회 확인 콜백함수 호출");
		if( httpRequest.readyState == 4){
			if( httpRequest.status == 200){
				result3();
			}else console.log("AJAX 요청/응답 에러")
		}
	}
	function result3(){
		var resultvar3 = JSON.parse(httpRequest.responseText);
		
		if( resultvar3 != null ){
			$("#showid").text("아이디 : " + resultvar3 )
			$("#showid").css("color", "red")
		}else{
			$("#showid").text("조회하신 아이디가 없습니다.")
			$("#showid").css("color", "red")
		}
	}
});

</script>

<div style="width: 500px; margin:0 auto; height: 700px;">
<h1>아이디 찾기</h1>
<hr>
	<div class="form-group">
    	<label >이름</label>
    	<input type="text" id="name" name="name" class="form-control" required="required">
	</div>  
	<div class="form-group">
		<label>이메일</label><br>
		<input type="email" id="email" name="email" class="form-control" required="required" style="width: 325px; display:inline-block; margin-right: 20px;"/>
		<button id="btnSendEmail" class="form-control" style="width: 150px; display:inline-block; background: #2AC1BC; color: white;">이메일 인증받기</button>
		<div id="emailv"></div>
	</div>
	
	<div class="form-group">
		<label>인증번호 입력</label><br>
		<input type="text" name="code" id="code" class="form-control"  required="required"  placeholder="인증번호를 입력하세요" style="width: 325px; display:inline-block; margin-right: 20px;"/>
		<button id="btnCodeVerify" class="form-control" style="width: 150px; display:inline-block; background: #2AC1BC; color: white;">확인</button>
		<div id="codev"></div>
	</div>

<!-- 아이디 조회 (AJAX)  -->
<div id="showid" style="font-size: 20px;"></div>
<hr>
<div style="text-align: center;">
<a href="/login" style="text-decoration: underline;">로그인</a> &emsp;
<a href="/forgetpw" style="text-decoration: underline;">비밀번호 찾기</a>
</div>
</div>

<%-- <c:import url="/WEB-INF/views/login/footer.jsp"></c:import> --%>