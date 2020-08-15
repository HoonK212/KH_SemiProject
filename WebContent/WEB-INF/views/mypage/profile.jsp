<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- header -->
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

<script type="text/javascript" >
$(document).ready(function(){
	
	$("#btnSave").click( function(){
		$("#target").submit();
	})
	
	$("#btnUpFile").click( function(){
		$("#upload").trigger("click");
	});

	$("#upload").change( function(e) {

		 var file = e.target.files // FileList 객체      
		 
		 if( !file[0].type.includes("image") ) {
		    alert("이미지가 아닙니다.")
		    e.target.value = null;
		    return false;
		 }
		 
		 // FileReader 객체 생성
		 var reader = new FileReader();
		 
		 // File 객체의 정보(내용물)을 모두 읽어서 메모리에 적재(load)한 이후 동작되도록 이벤트 리스너 작성
		 reader.onload = function(ev) {
		    console.log(ev)
		    console.log(ev.target)
		    
		    console.log("--------------------------------")
		    
		    console.log(ev.target.result)
		    
		    // 이미지가 한장만 유지됨
		    $("#profile_img").html( $("<img>").attr({
		          "src": ev.target.result,
		          "width": 300,
		          "height": 200
		    }));
		    
		 }
		 
		 // FileReader 객체를 이용한 File 객체 정보 읽기
		 reader.readAsDataURL(file[0]); // Blob 또는 File 형식으로 읽기
	})
var div = document.getElementById("profile_img"); 
var img = document.getElementById("pfimg"); 
var divAspect = 200 / 200;
var imgAspect = ${picture.picVer } / ${picture.picHor };

if (imgAspect <= divAspect) {
    // 이미지가 div보다 납작한 경우 세로를 div에 맞추고 가로는 잘라낸다
    var imgWidthActual = div.offsetHeight / imgAspect;
    var imgWidthToBe = div.offsetHeight / divAspect;
    var marginLeft = -Math.round((imgWidthActual - imgWidthToBe) / 2);
    img.style.cssText = 'width: auto; height: 100%; margin-left: ' + marginLeft + 'px;'
} else {
    // 이미지가 div보다 길쭉한 경우 가로를 div에 맞추고 세로를 잘라낸다
    img.style.cssText = 'width: 100%; height: auto; margin-left: 0;';
}

})

</script>

<style type="text/css">

.profile_container {
	width: 20%;
   	height: 450px;

 	display: inline-block; 
	float: left;

   	margin-top: 100px;
   	margin-right: 30px;
}

.img_upload {
	
 	width:200px; 
 	height:200px; 
	overflow: hidden;
	border-radius: 50%;
	background-color: #faf8f8;
}

.grade_container {
	margin-top: 30px;
}

.grade_container > div {
	width: 80%;
	height: 30px;
	text-align: center;
}

.menu_container{
	margin-top: 100px;
}

.menu_container h3 {
	width: 80%;
	height: 30px;
	text-align: center;
	vertical-align: middle;
	padding-top: 10px;
}
</style>

<section class="wrapper">
	<div class="profile_container">
	
		<div class="img_container">
			<form id="target" action="/editProfile" method="post" enctype="multipart/form-data">
				<div class="img_upload" id="profile_img">
				<a href="/editProfile"><img src="/upload_picture/${picture.picServer }" alt="프로필사진" id="pfimg" ></a>
				</div>
				<input type="text" name="userno" id="userno" value="${userno }" style="display: none;"/>
				<input type="file" name="upload" id="upload" style="display: none;"/>
			</form>
			<br>	
			
			<div class="img_upload_Btn">	
				<button type="button" id="btnUpFile" style="background-color:#faf8f8; width: 100px; height: 40px; border: 1px solid black;">프로필 등록</button>
				<button id="btnSave" style="background-color:#faf8f8; width: 100px; height: 40px; border: 1px solid black;">프로필 저장</button>
			</div>
		</div>
		
		<div class="grade_container">
			<div class="grade_email">
				<h3>${usernick }</h3>
			</div>
			<div class="grade_nick">
				<h3>${useremail }</h3>
			</div>
			<div class="grade_grade">
				<h3>${grade }
				<c:if test="${usergrade eq 1}">
				<img src="/resources/image/grade01.JPG" style="width: 30px; height: 40px; vertical-align:middle;"/></h3>
				</c:if>
				<c:if test="${usergrade eq 2}">
				<img src="/resources/image/grade02.JPG" style="width: 50px; height: 60px; vertical-align:middle;"/></h3>
				</c:if>
				<c:if test="${usergrade eq 3}">
				<img src="/resources/image/grade03.JPG" style="width: 50px; height: 60px; vertical-align:middle;"/></h3>
				</c:if>
				<c:if test="${usergrade eq 4}">
				<img src="/resources/image/grade04.JPG" style="width: 50px; height: 60px; vertical-align:middle;"/></h3>
				</c:if>
			</div>
		</div>
		
		<div class="menu_container">
			<div>
			<h3>나의 활동</h3>				
			<h3><a href="/editProfile" style="font-weight: lighter;">활동프리뷰</a></h3>				
			<h3><a href="/view/mywork" style="font-weight: lighter;">리뷰관리</a></h3>				
			<h3><a href="/view/myreport" style="font-weight: lighter;">신고내역</a></h3>				
			</div>
			<div style="margin-top: 30px;">
			<h3>회원 정보</h3>				
			<h3><a href="/view/info" style="font-weight: lighter;">개인정보</a></h3>				
			<h3><a href="/security" style="font-weight: lighter;">비밀번호변경</a></h3>				
			<h3><a href="/leavesite" style="font-weight: lighter;">사이트탈퇴</a></h3>				
			</div>
		</div>
	</div>