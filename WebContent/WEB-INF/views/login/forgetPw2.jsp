<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!-- header -->
<c:import url="/WEB-INF/views/login/header.jsp"></c:import>

<!--XMLHttpRequest 생성  -->
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	//메일일치 전역변수
	var checkemail = false;
	//메일발송 전역변수
	var emailsend = false;
	//인증코드 전역변수
	var checkcode = false;
	
	$("#email").blur(function(){
		var e = "useremail=" + $("#email").val();
		sendRequest("GET", "/checkemail", e, callback1 )
		
		function callback1(){
			console.log("메일일치 콜백함수 호출");
			if( httpRequest.readyState == 4 ){
				if( httpRequest.status == 200){
					result1();
				} else console.log("AJAX 요청/응답 에러")
			}
		}
		function result1(){
			var resultvar1 = JSON.parse(httpRequest.responseText);
			checkemail = resultvar1.result;
			
			if( checkemail == false) {
				$("#emailv").text( "회원가입시 등록한 이메일을 입력하세요." );
				$("#emailv").css( "color", 'red' );
			}
			if( checkemail == true ){
				$("#emailv").text( "이메일 확인 성공!" );
				$("#emailv").css( "color", 'blue' );
			}
		}
	})
	
	$("#btnSendEmail").click(function(){
		
			if( checkemail == false ){
				$("#emailv").text( "회원가입시 등록한 이메일을 입력하세요." );
				$("#emailv").css( "color", 'red' );
				return false;
			}

			var p = "useremail=" + $("#email").val();
			sendRequest("GET", "/send", p, callback2);

	})
	function callback2(){
		console.log("인증이메일 전송 콜백함수 호출");
		if( httpRequest.readyState == 4 ){
			if( httpRequest.status == 200){
				result2();
			} else console.log("AJAX 요청/응답 에러")
		}
	}
	function result2(){
		var resultvar2 = JSON.parse(httpRequest.responseText);
		emailsend = resultvar2.result;
		
		if( emailsend == true ){
			$("#emailv").text( "이메일을 발송하였습니다" );
			$("#emailv").css( "color", 'blue' );
		} 
		
		if( emailsend == false ){
			$("#emailv").text( "이메일 발송에 실패했습니다" );
			$("#emailv").css( "color", 'red' );
		} 
	}
	
	$("#btnCodeVerify").click(function(){
		var params = "useremail=" + $("#email").val() + "&code=" + $("#code").val();
		sendRequest("POST", "/send", params, callback3)
	});
	
	function callback3(){
		console.log("코드인증 콜백함수 호출");
		if( httpRequest.readyState == 4 ){
			if( httpRequest.status == 200){
				result3();
			} else console.log("AJAX 요청/응답 에러")
		}
	}
	function result3(){
		var resultvar3 = JSON.parse(httpRequest.responseText);
		checkcode = resultvar3.result;
		
		if( checkcode == true ){
			$("#codev").text("코드 인증 성공!")
			$("#codev").css("color", "blue")
		}
		
		if( checkcode == false ){
			$("#codev").text("코드 인증 실패")
			$("#codev").css("color", "red")
		}
	}
	$("#btnNext").click(function(){
		
		if( checkcode == true ){
			$("#codev").text("코드 인증 성공");
			$("#codev").css("color", "blue");
			location.href = "/changepw";
		}
		if( checkcode == false){
			$("#codev").text("코드 인증 실패")
			$("#codev").css("color", "red")
			return false;
		}
	})
	
});
</script>

<div style="width: 500px; margin:0 auto; height: 700px;">
<h1 style="text-align: center">비밀번호찾기 (본인인증)</h1>
<hr>
	<div class="form-group">
		<label>이메일</label><br>
		<input type="email" id="email" name="email" class="form-control" placeholder="회원이메일 입력"  required="required" style="width: 325px; display:inline-block; margin-right: 20px;"/>
		<button type="button" id="btnSendEmail" class="form-control" style="width: 150px; display:inline-block; background: #2AC1BC; color: white;">이메일 인증받기</button>
		<div id="emailv"></div>
	</div>
	
	<div class="form-group">
		<label>인증번호 입력</label><br>
		<input type="text" id="code" name="code" class="form-control"  placeholder="인증번호 입력" style="width: 325px; display:inline-block; margin-right: 20px;"/>
		<button class="form-control" id="btnCodeVerify" style="width: 150px; display:inline-block; background: #2AC1BC; color: white;">확인</button>
		<div id="codev"></div>
	</div>
	<div style="text-align: center">
		<button class="form-control" id="btnNext" style="background: #2AC1BC; color: white; width:150px; display: inline-block">다음</button>
	</div>
</div>


<%-- <c:import url="/WEB-INF/views/login/footer.jsp"></c:import> --%>