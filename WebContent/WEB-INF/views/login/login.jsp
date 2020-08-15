<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!-- header -->
<c:import url="/WEB-INF/views/login/header.jsp"></c:import>

<!--XMLHttpRequest 생성  -->
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<script type="text/javascript">
function login(){
	
	if( id.value == "" ){
		a.innerText = "아이디를 입력하세요";
		a.setAttribute("style", 'color: red;')
		return false;
	} else if( pw.value == ""){
		b.innerText = "비밀번호를 입력하세요";
		b.setAttribute('style', "color: red;")
		return false;
	}
	
	var params = "id=" + id.value + "&pw=" + pw.value;
	console.log(params);
	
	sendRequest("POST", "/login", params, callback);
	
};	
function callback() {
	
	if( httpRequest.readyState == 4 ){
		if( httpRequest.status == 200){
			loginResult();
		} else console.log("AJAX 요청/응답 에러")
	}
};	
function loginResult() {
	
	var loginResult = JSON.parse(httpRequest.responseText);

	//로그인성공
	if( loginResult.login ){
		//일반사용자
		if(loginResult.userAuth == 1 ){ location.href="/main" }
		//프랜차이즈관리자
		if(loginResult.userAuth == 2 ){ location.href="/fran"}
	}
	
	//로그인실패
	if( !loginResult.login ){
		result.innerText = "로그인실패! 입력하신 아이디와 비밀번호가 일치하지 않습니다."
		result.setAttribute("style", "color:red")
	}
	
	//정지중인 계정
	if( loginResult.report ){
		result.innerText = "계정 정지중입니다."
		result.setAttribute("style", "color:red")
	}
	
};

window.onload = function(){

id.addEventListener("blur", function( event ) {
	if( id.value != "" ){
		a.innerText = ""
	}else{
		a.innerText = "아이디를 입력하세요";
		a.setAttribute("style", 'color: red;')
	}
});
pw.addEventListener("blur", function( event ) {
	if( pw.value != "" ){
		b.innerText = ""
	}else{
		b.innerText = "비밀번호를 입력하세요";
		b.setAttribute('style', "color: red;")
	}
}, true);
	
}
</script>


<!-- css  -->
<style type="text/css">
.login_wrapper{
	width: 500px;
	margin: 0 auto;
	height: 500px;
	text-align: center;
}

.login_forget > a{
	margin-right: 30px;
	color: black;
}

</style>

<div class="login_wrapper">
   	<div><input type="text" id="id" class="form-control" placeholder="아이디 입력" ></div>
   	<div id="a"></div><br>
   	<div><input type="password" id="pw" class="form-control" placeholder="비밀번호 입력"></div>
	<div id="b"></div><br>
	<div id="result"></div>
	<button onclick="login();" class="btn btn-block" style="background: #2AC1BC; color: white;">로그인</button>
<br><hr><br>

<div class="login_forget">
<a href="/forgetid">아이디찾기</a>
<a href="/forgetpw">비밀번호찾기</a>
<a href="/join">회원가입</a> 
</div>
</div>


<%-- <c:import url="/WEB-INF/views/login/footer.jsp"></c:import> --%>
</html>