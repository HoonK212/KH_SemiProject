<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:import url="/WEB-INF/views/layout/header.jsp" />
<c:import url="/WEB-INF/views/layout/header_slide.jsp" />
<script type="text/javascript">
$(document).ready(function(){
	
	var div = document.getElementById("rec_img1"); // 이미지를 감싸는 div
	var img = document.getElementById("img1"); // 이미지
	
	var divAspect = 200 / 200; 
	var imgAspect = ${image[0].imgVer } / ${image[0].imgHor }
	
	if (imgAspect <= divAspect) {
	    // 이미지가 div보다 납작한 경우 세로를 div에 맞추고 가로는 잘라낸다
	    var imgWidthActual = div.offsetHeight / imgAspect;
	    var imgWidthToBe = div.offsetHeight / divAspect;
	    var marginLeft = -Math.round((imgWidthActual - imgWidthToBe) / 2);
	    img.style.cssText = 'width: auto; height: 100%; margin-left: '
	                      + marginLeft + 'px;'
	} else {
	    // 이미지가 div보다 길쭉한 경우 가로를 div에 맞추고 세로를 잘라낸다
	    img.style.cssText = 'width: 100%; height: auto; margin-left: 0;';
	}
})	
</script> 





<section class="wrapper">
<div class="rec_container">
	<div class="rec_intro">
		<h2>각 음식 종류 별 평점/가격/한줄평 수 등을 토대로 추천메뉴 선정</h2>
	</div>
	
<div class="rec_content">
<ul>

	<li><div class="rec_img" id="rec_img1"><img alt="" src="/upload/${image[0].imgServer }" id="img1"></div><div class="rec_con" id="rec_con1">메뉴 소개 영역 : ${menu[0] }</div></li>

	<li><div class="rec_img" id="rec_img2"><img alt="" src="/upload/${image[12].imgServer }" id="img2"></div><div class="rec_con" id="rec_con2">메뉴 소개영역 : ${menu[12] }</div></li>

	<li><div class="rec_img" id="rec_img3"><img alt="" src="/upload/${image[2].imgServer }" id="img3"></div><div class="rec_con" id="rec_con3">메뉴 소개영역 : ${menu[2] }</div></li>

	<li><div class="rec_img" id="rec_img4"><img alt="" src="/upload/${image[3].imgServer }" id="img4"></div><div class="rec_con" id="rec_con4">메뉴 소개영역 : ${menu[3] }</div></li>

</ul>
</div>

</div>
</section>


<c:import url="/WEB-INF/views/layout/footer.jsp" />
