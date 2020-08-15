<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<c:import url="/WEB-INF/views/fran_layout/header.jsp" />

<c:import url="/WEB-INF/views/fran_layout/header_menu.jsp" />


<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/fran/list");
	});
	
	
});
</script>


<div class="body-content-view">
<div class="container">

<fieldset>
<legend>
<span class="badge"> 게시글 상세보기 </span>
</legend> 
</fieldset>

<table class="table table-bordered">
<tr>
<td class="info">회사명</td><td colspan="3">${franname }</td>
</tr>

<tr>
<td class="info">메뉴명</td><td colspan="3">${viewMenu.menuName }</td>
</tr>

<tr>
<td class="info">Blind 상태</td><td>${viewMenu.menuBlind }</td>
<td class="info">메뉴 번호</td><td>${viewMenu.menuNo }</td>
</tr>

<tr>
<td class="info">승인 상태</td><td>${viewMenu.menuStat }</td>
<td class="info">가격 </td><td>${viewMenu.menuCost }</td>
</tr>

<tr>
<td class="info">등록 일자</td><td colspan="3">${viewMenu.menuDate }</td>
</tr>

<tr><td class="info"  colspan="4">메뉴 소개글</td></tr>

<tr><td colspan="4">${viewMenu.menuInfo }</td></tr>


<tr><td class="info"  colspan="4">메뉴 이미지</td></tr>
<tr>
<td colspan="4"> 
<div id="preview" >
<img src="/upload/${image.imgServer }" alt="프로필사진" id="ImageBoxByview">
</div>
</td>
</tr>



</table>

<div class="text-center" id="list-button">	
	<button id="btnList" class="btn btn-primary">목록으로</button>
</div>

</div>


</div>


<c:import url="/WEB-INF/views/fran_layout/footer.jsp" />