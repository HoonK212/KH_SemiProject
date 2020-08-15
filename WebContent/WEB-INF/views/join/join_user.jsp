<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
    
<!--header-->    
<c:import url="/WEB-INF/views/login/header.jsp"></c:import>

<!--XMLHttpRequest 생성  -->
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	//아이디중복 전역변수
	var checkid = false;
	//이메일발송 전역변수
	var emailsend = false;
	//인증코드 전역변수
	var checkcode = false;
	
	
	//정규식 전역변수
	var regex01 = /^[a-zA-Zㄱ-힣0-9]{1,15}$/
	var regex02 = /^[a-zA-Zㄱ-힣0-9]{1,20}$/
	var regex03 = /^[a-zA-Zㄱ-힣0-9]{1,10}$/
	var regex04 = /^[a-zA-Zㄱ-힣0-9]{1,10}$/
	var regex05 = /^[0-9]{4,4}$/
	var regex06 = /^[0-9]{2,2}$/
	var regex07 = /^[0-9]{4,4}$/
	var regex08 = /^[0-9]{4,4}$/
	
	
	//회원가입 클릭이벤트
	$("#btnJoin").click(function(){
		
	//--------필수정보입력---------
		if( $("#id").val() == "" ){
			$("#idv").text( "필수정보입니다." ); 
			$("#idv").css( "color", 'red' ); 
			return false;
		}
		if( $("#pw").val() == "" ){
			$("#pwv").text( "필수정보입니다." ); 
			$("#idv").css( "color", 'red' ); 
			return false;
		}
		if( $("#pwConfirm").val() == "" ){ 
			$("#pwcv").text( "비밀번호 재확인이 필요합니다." ); 
			$("#pwcv").css( "color", 'red' ); 
			return false;
		}
		if(  $("#name").val() == "" ){
			$("#namev").text( "필수정보입니다." ); 
			$("#namev").css( "color", 'red' ); 
			return false;
		}
		if( $("#nick").val() == ""  ){
			$("#nickv").text( "필수정보입니다." ); 
			$("#nickv").css( "color", 'red' ); 
			return false;
		}
		if( $("#year").val() == "" ){
			$("#yearv").text( "필수정보입니다." ); 
			$("#yearv").css( "color", 'red' ); 
			return false;
		}
		if( $("#month").val() == "" ){
			$("#monthv").text( "필수정보입니다." ); 
			$("#monthv").css( "color", 'red' ); 
			return false;
		}
		if( $("#date").val() == "" ){
			$("#datev").text( "필수정보입니다." ); 
			$("#datev").css( "color", 'red' ); 
			return false;
		}
		if( $("#gender").val() == "" ){
			$("#genderv").text( "필수정보입니다." ); 
			$("#genderv").css( "color", 'red' ); 
			return false;
		}
		if( $("#email").val() == "" ){
			$("#emailv").text( "필수정보입니다." ); 
			$("#emailv").css( "color", 'red' ); 
			return false;
		}
		if( $("#tel_02").val() == "" ){
			$("#telv_02").text( "필수정보입니다." ); 
			$("#telv_02").css( "color", 'red' ); 
			return false;
		}
		if( $("#tel_03").val() == "" ){
			$("#telv_03").text( "필수정보입니다." ); 
			$("#telv_03").css( "color", 'red' ); 
			return false;
		}
	//-------------------------------
	//아이디중복
		if(checkid == false) {
			$("#idv").text( "이미 사용중인 아이디입니다." );
			$("#idv").css( "color", 'blue' ); 
			return false;
		}
	//비밀번호재확인
		if( $("#pw").val() != $("#pwConfirm").val() ){
			$("#pwcv").text( "비밀번호가 일치하지 않습니다." ); 
			$("#pwcv").css( "color", 'red' ); 
			return false;
		}
	//이메일인증
		if( checkcode == false ){
			$("#emailCodev").text( "이메일 인증이 필요합니다." ); 
			$("#emailCodev").css( "color", 'red' ); 
			return false;
		}
	//----------정규식검증-----------
		if( !regex01.test($("#id").val()) ){
			$("#idr").text( "아이디는 15글자 이하로 가능합니다." );
			$("#idr").css( "color", 'red' );
			return false;
		}
		if( !regex02.test($("#pw").val()) ){
			$("#pwv").text( "비밀번호는 20글자 이하로 가능합니다." );
			$("#pwv").css( "color", 'red' );
			return false;
		}
		if( !regex03.test($("#name").val()) ){
			$("#namev").text( "이름은 10글자 이하로 가능합니다." );
			$("#namev").css( "color", 'red' );
			return false;
		}
		if( !regex04.test($("#nick").val()) ){
			$("#nickv").text( "닉네임은 10글자 이하로 가능합니다." );
			$("#nickv").css( "color", 'red' );
			return false;
		}
		if( !regex05.test($("#year").val()) ){
			$("#yearv").text( "4자의 숫자만 가능합니다." );
			$("#yearv").css( "color", 'red' );
			return false;
		}
		if( !regex06.test($("#date").val()) ){
			$("#datev").text( "2자의 숫자만 가능합니다. 날짜 '1'~'9'는 '01'~'09'로 표기" );
			$("#datev").css( "color", 'red' );
			return false;
		}
		if( !regex07.test($("#tel_02").val()) ){
			$("#telv_02").text( "4자의 숫자만 가능합니다." );
			$("#tev_02").css( "color", 'red' );
			return false;
		}
		if( !regex08.test($("#tel_03").val()) ){
			$("#telv_03").text( "4자의 숫자만 가능합니다." );
			$("#telv_03").css( "color", 'red' );
			return false;
		}
		
	});
	

	//-------------포커스 잃을때-------------
	$("#id").blur(function(){
		//필수정보
		if( $("#id").val() == "" ){	
			$("#idv").text( "필수정보입니다." );
			$("#idv").css( "color", 'red' );
			$("#idr").html( "<div></div>" );
		}else{
			//정규식검증
			if( !regex01.test($(this).val()) ){
				$("#idr").text( "아이디는 15글자 이하로 가능합니다." );
				$("#idr").css( "color", 'red' );
				$("#idv").html( "<div></div>" );
			}else{
				$("#idr").html( "<div></div>" );
				//아이디중복확인
				var params =  "id=" + $("#id").val();
				sendRequest("POST", "/doublecheckid", params, callbackID);
			}
		}
	});
	//---------아이디중복확인 콜백------------
	function callbackID(){
		console.log("아이디중복확인 콜백함수 호출");
		if( httpRequest.readyState == 4 ){
			if( httpRequest.status == 200){
				idCheckResult();
			} else console.log("AJAX 요청/응답 에러")
		}
	}
	//---------아이디중복확인 결과-------------
	function idCheckResult() {
		
		var idCheckResult = JSON.parse(httpRequest.responseText);
		console.log(idCheckResult);
		
		checkid = idCheckResult.result
		
		if(checkid == true ){
			$("#idv").text( "사용가능한 아이디입니다." );
			$("#idv").css( "color", 'blue' );
		} 
		
		if(checkid == false ){
			$("#idv").text( "이미 사용중인 아이디입니다." );
			$("#idv").css( "color", 'red' );
		} 
	};
	$("#pw").blur(function() {
		
		//필수정보
		if( $("#pw").val() == "" ){	
			$("#pwv").text( "필수정보입니다." );
			$("#pwv").css( "color", 'red' );
		}else{
			//정규식검증
			if( !regex02.test($(this).val()) ){
				$("#pwv").text( "비밀번호는 20글자 이하로 가능합니다." );
				$("#pwv").css( "color", 'red' );
			}else{
				$("#pwv").html( "<div></div>" );
			}
		}
	});
	//비밀번호 재확인
	$("#pwConfirm").blur(function() {
		
		//필수정보
		if( $("#pwConfirm").val() == "" ){
			$("#pwcv").text( "비밀번호 재확인이 필요합니다." );
			$("#pwcv").css( "color", 'red' );
		}else{
			//비밀번호일치
			if( $("#pw").val() != $("#pwConfirm").val() ){
				$("#pwcv").text( "비밀번호가 일치하지 않습니다!" );
				$("#pwcv").css( "color", "red" );
			} else{
				$("#pwcv").html( "<div></div>" );
			}
		}
	});
	$("#name").blur(function(){
			
		//필수정보
		if( $("#name").val() == "" ){	
			$("#namev").text( "필수정보입니다." );
			$("#namev").css( "color", 'red' );
		}else{
			//정규식검증
			if( !regex03.test($(this).val()) ){
				$("#namev").text( "이름은 10글자 이하로 가능합니다." );
				$("#namev").css( "color", 'red' );
			}else{
				$("#namev").html( "<div></div>" );
			}
		}
	});
	$("#nick").blur(function(){
			
		//필수정보
		if( $("#nick").val() == "" ){	
			$("#nickv").text( "필수정보입니다." );
			$("#nickv").css( "color", 'red' );
		}else{
			//정규식검증
			if( !regex04.test($(this).val()) ){
				$("#nickv").text( "이름은 10글자 이하로 가능합니다." );
				$("#nickv").css( "color", 'red' );
			}else{
				$("#nickv").html( "<div></div>" );
			}
		}
	});
	$("#year").blur(function(){
		//필수정보
		if( $("#year").val() == "" ){
			$("#yearv").text( "필수정보입니다." ); 
			$("#yearv").css( "color", 'red' ); 
		}else {
			//정규식검증
			if( !regex05.test( $(this).val() ) ){
				$("#yearv").text( "4자리 숫자만 입력가능합니다." ); 
				$("#yearv").css( "color", 'red' ); 
			}else{
				$("#yearv").html( "<div></div>" ); 
			}
		}
	});
	$("#month").blur(function(){
			
		//필수정보
		if( $("#month").val() == "" ){
			$("#monthv").text( "필수정보입니다." ); 
			$("#monthv").css( "color", 'red' ); 
		}else{
			$("#monthv").html( "<div></div>" ); 
		}
	});
	$("#date").blur(function(){
			
		//필수정보
		if( $("#date").val() == "" ){
			$("#datev").text( "필수정보입니다." ); 
			$("#datev").css( "color", 'red' ); 
		}else{
			//정규식검증
			if( !regex06.test($(this).val()) ){
				$("#datev").text( "2자리의 숫자만 입력가능합니다. 날짜 '1'~'9'는 '01'~'09'로 표기" ); 
				$("#datev").css( "color", 'red' ); 
			}else{
				$("#datev").html( "<div></div>" ); 
			}
		}
	});
	$("#gender").blur(function(){
			
		if( $("#gender").val() != "" ){
			$("#genderv").html( "<div></div>" ); 
		}else{
			$("#genderv").text( "필수정보입니다." ); 
			$("#genderv").css( "color", 'red' ); 
		}
	});
	$("#email").blur(function(){
			
		if( $("#email").val() != "" ){
			$("#emailv").html( "<div></div>" ); 
		}else{
			$("#emailv").text( "필수정보입니다." ); 
			$("#emailv").css( "color", 'red' ); 
		}
	});
	$("#emailCode").blur(function(){
			
		if( $("#emailCode").val() != "" ){
			$("#emailCodev").html( "<div></div>" ); 
		}else{
			$("#emailCodev").text( "이메일 인증은 필수입니다." ); 
			$("#emailCodev").css( "color", 'red' ); 
		}
	});
	$("#tel_02").blur(function(){
			
		//필수정보
		if( $("#tel_02").val() == "" ){
			$("#telv_02").text( "필수정보입니다." ); 
			$("#telv_02").css( "color", 'red' ); 
		}else{
			//정규식검증
			if( !regex07.test($(this).val()) ){
				$("#telv_02").text( "4자리 숫자만 입력가능합니다." ); 
				$("#telv_02").css( "color", 'red' ); 
			}else{
				$("#telv_02").html( "<div></div>" ); 
			}
		}
	});
	$("#tel_03").blur(function(){
			
		//필수정보
		if( $("#tel_03").val() == "" ){
			$("#telv_03").text( "필수정보입니다." ); 
			$("#telv_03").css( "color", 'red' ); 
		}else{
			//정규식검증
			if( !regex08.test($(this).val()) ){
				$("#telv_03").text( "4자리 숫자만 입력가능합니다." ); 
				$("#telv_03").css( "color", 'red' ); 
			}else{
				$("#telv_03").html( "<div></div>" ); 
			}
		}
	});
//--------- 이메일 보내기 ----------
$("#btnSendEmail").click( function(){
	
	if($("#email").val() == "" ){
		$("#emailv").text( "이메일을 입력하세요." );
		$("#emailv").css( "color", 'red' );
		return false;
	}
	
	var params = "username=" + $("#name").val() + "&" + "useremail=" + $("#email").val();
	console.log(params);
	sendRequest("GET", "/send", params, callbackEmail);
}) 

function callbackEmail(){
	console.log("이메일 발송 콜백함수 호출");
	if( httpRequest.readyState == 4 ){
		if( httpRequest.status == 200){
			emailSendResult();
		} else console.log("AJAX 요청/응답 에러")
	}
}
function emailSendResult(){
	var emailSendResult = JSON.parse(httpRequest.responseText);
	console.log(emailSendResult);
	
	emailsend = emailSendResult.result
	
	if( emailsend == true ){
		$("#emailv").text( "이메일을 발송하였습니다" );
		$("#emailv").css( "color", 'blue' );
	} 
	
	if( emailsend == false ){
		$("#emailv").text( "이메일 발송에 실패했습니다" );
		$("#emailv").css( "color", 'red' );
	} 
}
// -------- 인증코드 인증하기 ------------
$("#btnCodeVerify").click( function(){
	var params = "useremail=" + $("#email").val() + "&" + "code=" + $("#code").val();
	sendRequest("POST", "/send", params, callbackCode);
})
function callbackCode(){
	console.log("코드인증 콜백함수 호출");
	if( httpRequest.readyState == 4 ){
		if( httpRequest.status == 200){
			codeCheckResult();
		} else console.log("AJAX 요청/응답 에러")
	}
}
function codeCheckResult(){
	var codeCheckResult = JSON.parse(httpRequest.responseText);
	console.log(codeCheckResult);
	
	checkcode = codeCheckResult.result
	
	if(checkcode == true) {
		$("#emailCodev").text( "코드 인증 성공!" );
		$("#emailCodev").css( "color", 'blue' );
	} 
	
	if(checkcode == false) {
		$("#emailCodev").text( "코드 인증 실패!" );
		$("#emailCodev").css( "color", 'red' );
	}
}

});
</script>


<div style="width: 500px; margin:0 auto; height: 1200px;">

<form action="/userjoin" method="POST">

<div class="form-group">
    <label>아이디</label>
    <input type="text" name="id" id="id" class="form-control">
    <div id="idv"></div>
    <div id="idr"></div>
</div>
<div class="form-group">
    <label>비밀번호</label>
    <input type="password" name="pw" id="pw" class="form-control" >
	<div id="pwv"></div>
</div>
<div class="form-group">
    <label>비밀번호 재확인</label>
    <input type="password" id="pwConfirm" class="form-control" >
	<div id="pwcv"></div>
</div>
<div class="form-group">
    <label>이름</label>
    <input type="text" name="name" id="name" class="form-control">
	<div id="namev"></div>
</div>
<div class="form-group">
    <label>닉네임</label>
    <input type="text" name="nick" id="nick" class="form-control">
	<div id="nickv"></div>
</div>

<div class="form-group">
	<label>생년월일</label><br>
	<input type="text" name="year" id="year" class="form-control" placeholder="년(4자)" style="width: 150px; display:inline-block; margin-right: 19px;" />
               
	<select name="month" id="month" class="form-control" style="width: 150px; display:inline-block; margin-right: 19px;">
	<option value="">월</option>
	<option value="1">01</option>
	<option value="2">02</option>
	<option value="3">03</option>
	<option value="4">04</option>
	<option value="5">05</option>
	<option value="6">06</option>
	<option value="7">07</option>
	<option value="8">08</option>
	<option value="9">09</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
	</select>

	<input type="text" name="date" id="date" class="form-control" placeholder="일" style="width: 150px; display:inline-block;"/><br>
	<div id="yearv"></div><div id="monthv"></div><div id="datev"></div>
</div>	

<div class="form-group">
    <label>성별</label>
	<select name="gender" id="gender" class="form-control" >
    	<option value="">선택</option>
    	<option value="m">남</option>
    	<option value="f">여</option>
	</select>
	<div id="genderv"></div>
</div>

<div class="form-group">
	<label>이메일</label><br>
	<input type="email" name="email" id="email" class="form-control" style="width: 325px; display:inline-block; margin-right: 20px;"/>
	<button type="button" id="btnSendEmail" class="form-control" style="width: 150px; display:inline-block; background: #2AC1BC; color: white;">이메일 인증받기</button>
	<div id="emailv"></div>
</div>

<div class="form-group">
	<label>인증번호 입력</label><br>
	<input type="text" name="code" id="code" class="form-control" style="width: 325px; display:inline-block; margin-right: 20px;" placeholder="인증번호를 입력하세요" />
	<button type="button" id="btnCodeVerify" class="form-control" style="width: 150px; display:inline-block; background: #2AC1BC; color: white;">확인</button>
	<div id="emailCodev"></div>
</div>

<div class="form-group">
<label>전화번호</label><br>
<select name="tel_01" id="tel_01" class="form-control" style="width: 150px; display:inline-block; margin-right: 19px;">
	<option value="010">010</option>
	<option value="011">011</option>
	<option value="016">016</option>
	<option value="017">017</option>
</select>
<input type="text" name="tel_02" id="tel_02" class="form-control"  style="width: 150px; display:inline-block; margin-right: 19px;"/> 
<input type="text" name="tel_03" id="tel_03" class="form-control"  style="width: 150px; display:inline-block;"/><br>
<div id="telv_02"></div><div id="telv_03"></div>
</div>

<div class="form-group">
<label>관심프랜차이즈</label>
<select name="franName" id="franName" class="form-control">
     <option value="">선택</option>
     	<c:forEach var="i" items="${listFran }" begin="0" end="${fn:length(listFran)}">
    <option value="${i.franName }">${i.franName }</option>
    </c:forEach> 
</select>
</div>

<hr>
<button id="btnJoin" class="btn btn-lg btn-block" style="background: #2AC1BC; color: white;">가입하기</button>
</form>
</div>

<%-- <c:import url="/WEB-INF/views/login/footer.jsp"></c:import> --%>
