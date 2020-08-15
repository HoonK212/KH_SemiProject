<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="/resources/css/board/board.css">

<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<script type="text/javascript">
$(document).ready(function() {
	//검색 버틀 클릭
	$("#btnSearch").click(function() {
		location.href="/view/posts?search="+$("#search").val();
	});
	
});
</script>

<section class="wrapper">
<div class="board">

	<!-- 세션 여부로 글쓰기 제한 -->
	<c:set var="path" value="location.href='/add/posts'"></c:set>
	<c:if test="${login eq true }">
	</c:if>
	
	<c:if test="${login != true }">
		<c:set var="path" value="location.href='/login'"></c:set>
	</c:if>
	
	<!-- 자유게시판 Title -->
	<div class="board_top">
		<h3 style="display: inline; margin-right: 250px;" >공지사항 참고하시고 사용자 질문 올려주세요</h3>
		<input type="text" id="search" style="line-height: 25px"/>
		<button class="button" id="btnSearch"">검색</button><button class="button" onclick="${path}">글쓰기</button>
	</div>
	
	<!-- 자유게시판 목록 -->
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>아이디</th>
			<th>조회수</th>
			<th>작성일자</th>
			<th>답변여부</th>
		</tr>
		
		<c:forEach var="item" items="${boardList}">
			<tr>
				<td width="10%"><c:out value="${item.key.bdNo}" /></td>
				<td width="50%"><a href="/detail/posts?bdNo=${item.key.bdNo }"><c:out value="${item.key.bdTitle}" /></a></td>
				<td width="10%"><c:out value="${item.value}" /></td>
				<td width="8%"><c:out value="${item.key.bdCnt}" /></td>
				<td width="14%"><c:out value="${item.key.bdDate}" /></td>
				<td width="8%"><c:out value="${item.key.bdYn}" /></td>
			</tr>
		</c:forEach>
	</table>
</div>
</section>
<c:import url="/WEB-INF/views/board/paging_posts.jsp" />
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>