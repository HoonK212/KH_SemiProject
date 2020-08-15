<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css"  href="/resources/css/paging.css" >

<div class="center">
<div class="pagination">
	<!-- 첫 페이지로 가기 -->
	<c:if test="${paging.curPage ne 1 }"><!-- 첫 페이지가 아닐 때 보여줌 -->
	<a href="/view/posts?curPage=1&search=${param.search }">&larr;</a>
	</c:if>
	
	
	<!-- 이전 페이징 리스트로 가기 -->
	<c:if test="${paging.startPage gt paging.pageCount }">
	<a href="/view/posts?curPage=${paging.startPage - paging.pageCount }&search=${param.search }">&laquo;</a>
	</c:if>

	
	
	<!-- 이전 페이지로 가기 -->
	<c:if test="${paging.curPage ne 1 }">
	<a href="/view/posts?curPage=${paging.curPage - 1 }&search=${param.search }">&lt;</a>
	</c:if>
	


	<!-- 페이징 리스트 -->
	<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
	
	<!-- 보고 있는 페이지번호 강조 (.active) -->
	<c:if test="${paging.curPage eq i }">
	<a href="/view/posts?curPage=${i }&search=${param.search }">${i }</a>
	</c:if>
	
	<!-- 보고 있는 페이지번호가 아니면 평소 모양으로 보여주기 -->
	<c:if test="${paging.curPage ne i }">
	<a href="/view/posts?curPage=${i }&search=${param.search }">${i }</a>
	</c:if>
	
	</c:forEach>
	
	
	
	<!-- 다음 페이지로 가기 -->
	<c:if test="${paging.curPage ne paging.totalPage }">
	<a href="/view/posts?curPage=${paging.curPage + 1 }&search=${param.search }">&gt;</a>
	</c:if>
	
	
	
	<!-- 다음 페이징 리스트로 가기 -->
	<c:if test="${paging.endPage ne paging.totalPage }">
	<a href="/view/posts?curPage=${paging.startPage + paging.pageCount }&search=${param.search }">&raquo;</a>
	</c:if>

	<c:if test="${paging.endPage eq paging.totalPage }">
	<a>&raquo;</a>
	</c:if>
	
	
	
	<!-- 마지막 페이지로 가기 -->
	<c:if test="${paging.curPage ne paging.totalPage }"><!-- 끝 페이지가 아닐 때 보여준다 -->
	<a href="/view/posts?curPage=${paging.totalPage }&search=${param.search }">&rarr;</a>
	</c:if>
</div>
</div>