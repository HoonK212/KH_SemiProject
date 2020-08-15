<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	

	var url = location.href.substr(location.href.lastIndexOf("/")+1);
	
	if("fran"== url){
		$("#nav_bar_sub1").addClass("active");
	}else if("list" == url){
		$("#nav_bar_sub1").addClass("active");
	}else if ("menu" == url) {
		$("#nav_bar_sub2").addClass("active");
	}else if ("blind" == url){
		$("#nav_bar_sub3").addClass("active");
	}
	
	var url2 = location.href.substr((location.href.lastIndexOf("/")+1 ),+4);
	if("view" == url2){
		$("#nav_bar_sub1").addClass("active");
	}else if("list" == url2){
		$("#nav_bar_sub1").addClass("active");
	}
	

});

</script>
<div>
<ul class="nav nav-pills nav-justified" id="nav_bar">
	<li role="presentation" id="nav_bar_sub1"><a href="/fran/list">게시물 관리</a></li>
	<li role="presentation" id="nav_bar_sub2"><a href="/fran/apply/menu">신메뉴 등록</a></li>
	<li role="presentation" id="nav_bar_sub3"><a href="/fran/apply/blind">Blind 메뉴 신청</a></li>
</ul>
</div>


