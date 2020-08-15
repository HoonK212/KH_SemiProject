<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<div class="body-content-list">
<table class="table table-striped table-hover table-condensed" >
<thead>
<tr>
	<th style="width: 15%;">번호</th>
	<th style="width: 30%;">메뉴명</th>
	<th style="width: 20%;">조회수</th>
	<th style="width: 15%;">평점</th>
	<th style="width: 20%;">등록 일자</th>
</tr>
</thead>

<tbody>
<c:forEach begin="1" end="20" step="1" var="i">
<tr>
	<td>${i }</td>
	<td><a href="#">황금 뿌링클</a></td>
	<td>624</td>
	<td>4.5</td>
	<td>2020-06-18</td>
</tr>
</tbody>
</c:forEach>
</table>
</div>
