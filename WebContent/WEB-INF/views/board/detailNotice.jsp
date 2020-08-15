<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" type="text/css"  href="/resources/css/board/board.css" >   
<c:import url="/WEB-INF/views/layout//header.jsp"></c:import>
<section class="wrapper">
<div class="board">

<div class="board_top">
<h1 style="padding-bottom: 20px;">공지 상세보기</h1>
</div>
<table>

<tr><th width="20%">제목</th><td width="80%"><c:out value="${board.bdTitle}" /></td></tr>
<tr><td></td></tr>
<tr><th colspan="2">공지 내용</th></tr>
<tr><td colspan="2"><textarea cols="112" rows="10" disabled="disabled" style="padding: 20px;"><c:out value="${board.bdQuestion}" /></textarea></td></tr>
</table>
<button style="margin-left: 955px;" class="button" onclick="location.href='/view/notice'">목록</button>


</div>
</section>
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>