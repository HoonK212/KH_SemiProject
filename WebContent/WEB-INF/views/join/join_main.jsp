<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<style>
.button {
	display: inline-block;
	height: 100px;
	width: 500px;
/* 	height: 30px; */
	text-align: center;
	text-decoration: none;
	line-height: 27px;
	outline: none;
/* 	margin:10px 10px; */
/* 	margin-left: 650px; */
border-color: inherit;
}
.button::before,
.button::after {
	position: absolute;
	z-index: -1;
	display: block;
	content: '';
}
.button,
.button::before,
.button::after {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	-webkit-transition: all .3s;
	transition: all .3s;
}

.button {
	background-color: #6d819c;
	background-color: #2AC1BC;
	
	color: #fff;
	font-weight: bolder;
}


	
</style>
   
    
<!--header-->    
<c:import url="/WEB-INF/views/login/header.jsp"></c:import>


<div style="width: 500px; margin:0 auto; height: 1200px; text-align: center;">
<h1><a href="/userjoin"><button class="button" style="border-radius: 30px; border: 0px;">일반인 회원가입</button></a></h1><br>
<h1><a href="/franjoin"><button class="button" style="border-radius: 30px; border: 0px;">프랜차이즈 회원가입</button></a></h1><br>
</div>

<c:import url="/WEB-INF/views/login/footer.jsp"></c:import>