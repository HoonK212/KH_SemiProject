<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css"  href="/resources/css/board/board.css" >    
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>
<section class="wrapper">
<div class="board">

<div class="board_top">
	<h1 style="padding-bottom: 20px;">게시판 작성</h1>
</div>

<form action="/add/posts" method="post">
<c:set var="item" value="${user }" />

<table>
<tr><th>아이디</th><td><c:out value="${item.userId }" /></td></tr>
<tr><th>닉네임</th><td><c:out value="${item.userNick }" /></td></tr>
<tr><th>제목</th><td><input type="text" name="title" style="width:100%; line-height: 20px;"/></td></tr>
<tr><th colspan="2">질문</th></tr>
<tr><td colspan="2"><textarea id="content" name="content" cols="112" rows="10" style="padding: 20px"></textarea></td></tr>
</table>

<button style="margin-left: 848px;" class="button" onclick="location.href='/add/posts'">작성</button>
<button class="button" onclick="javascript:history.go(-1)" style="margin-left: 0px;
    margin-right: 0px;">취소</button>

</form>

</div>
</section>
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>