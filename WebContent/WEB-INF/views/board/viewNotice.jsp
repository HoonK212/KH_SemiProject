<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="/resources/css/board/board.css">

<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<script type="text/javascript">
$(document).ready(function() {
	//검색 버틀 클릭
	$("#btnSearch").click(function() {
		location.href="/view/notice?search="+$("#search").val();
	});
	
});
</script>
<section class="wrapper">
<div class="board">
	<!-- 공지사항 Title -->
	<div class="board_top">
		<h3 style="display: inline; margin-right: 630px;" >공지사항</h3>
		<input type="text" id="search" style="line-height: 25px"/><button class="button" id="btnSearch">검색</button>
	</div>
	
	<!-- 공지사항 목록 -->
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성일자</th>
		</tr>
		
		<c:forEach var="item" items="${boardList}">
			<tr>
				<td width="15%"><c:out value="${item.bdNo}" /></td>
				<td width="55%"><a href="/detail/notice?bdNo=${item.bdNo }"><c:out value="${item.bdTitle}" /></a></td>
				<td width="15%"><c:out value="${item.bdCnt}" /></td>
				<td width="15%"><c:out value="${item.bdDate}" /></td>
			</tr>
		</c:forEach>
	</table>
</div>
</section>
<c:import url="/WEB-INF/views/board/paging_notice.jsp" />
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>