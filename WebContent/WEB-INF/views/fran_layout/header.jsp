<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> : : : 프랜차이즈 : : : </title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<link rel="stylesheet" type="text/css"  href="/resources/css/fran_yeonho.css" >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>

<div class="root">

<div class="header-container">

	<div class="header-topmenu">
		<button type="button" class="btn btn-success" onclick="location.href='/'">일반페이지로</button>
		<button type="button" class="btn btn-info"onclick="location.href='/logout'" >로그아웃</button>
	</div>
	
	<div class="header-wrapper">
	<a href="/fran"><img alt="메인로고" src="/resources/image/brand.png" id="logo"></a>
	</div>
	
	<div class="header-franid">
	<h2><mark>${franname }</mark>님 , 환영합니다.</h2>
	</div>
</div>
