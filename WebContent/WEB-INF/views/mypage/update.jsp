<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/views/mypage/profile.jsp"></c:import>

<style type="text/css">
.star-box {
	/* 별과 별 사이 공백 제거 */
    font-size: 0;
}

.star {
	/* width,height 적용가능하도록 변경 */
	display: inline-block;

	/* 별이 표현되는 영역 크기 */
	width: 25px;
    height: 55px;

	/* 투명한 별 표현 */
	background-image: url("/resources/image/star33.gif");
	background-repeat: no-repeat;
}

.star_left {
	/* 왼쪽 별 */
	background-position: 0 0;
	background-size: 200%;
}

.star_right {
	/* 오른쪽 별 */
	background-position: 100% 0;
	background-size: 200%;
}

.on {
	/* 채워진 별로 이미지 변경 */
	background-image: url("/resources/image/star44.gif");
}

.updatereview {
	margin-top: 262px;
	height: 700px;
	display: inline-block;
	width: 72%;
	text-align: center;
	font-size: 24px;
}


.updatereview h1 {
	margin-bottom:25px;
}

.updatereview hr {
	margin-top: 15px;
	margin-bottom: 30px;
}

.updatereview table{
	border: 1px solid black;
	border-collapse: collapse;
}

.updatereview th, td {
	text-align: center;
	padding: 10px;
	border: 1px solid black;
}

div.star-box {
	margin-top: 15px;
	margin-bottom: 15px;
}

textarea {
	width: 340px;
    height: 60px;
}

div.udp {
    margin-top: 20px;
}

div.udp > button {
	width: 100px;
	height: 40px;
}

</style>


<script type="text/javascript">
$(document).ready(function() {
	
	$("#goback").click( function(){
 		$(location).attr('href','/view/mywork');
	});
	
	var idx = 0;
	var updatescore = 0;
	
	//별 클릭 이벤트
	$(".star").click(function() {
		
		//클릭된 별이 몇 번째 칸인지 알아내기
		idx = $(this).index();
		
		//모두 투명하게 만들기
		$(".star").removeClass("on")
		
		//클릭이 된 곳까지 채워진 별로 만들기
		for(var i=0; i<=idx; i++) {
			$(".star").eq(i).addClass("on");
		}
		
		console.log("클릭된 별의 위치 : " + idx)
		console.log("점수로 변환 : " + (idx+1)/2)
		updatescore = (idx+1)/2;
		console.log("수정한 점수 : " + updatescore);
		
		$('#uds').attr('value', updatescore);
	})
	
	ss();	

});	

function ss() {
	
	$(".star").removeClass("on");
	for(var i=0; i<= ${myWork.star.starScore }*2-1; i++){
		$(".star").eq(i).addClass("on");
	};
}
</script>

<div class="updatereview">
<h1>리뷰 수정</h1>
<hr>
	<form action="/update/mywork" method="POST">
		<h1>메뉴 : ${myWork.menu.menuName }</h1>
		<div class="star-box">
		<span class="star star_left"></span>
		<span class="star star_right"></span>
		<span class="star star_left"></span>
		<span class="star star_right"></span>
		<span class="star star_left"></span>
		<span class="star star_right"></span>
		<span class="star star_left"></span>
		<span class="star star_right"></span>
		<span class="star star_left"></span>
		<span class="star star_right"></span>
		<input type="text" name="updatescore" id="uds" style="display: none;" value="${myWork.star.starScore }"/>
		</div>
		<textarea name="review">${myWork.review.reviewContent }</textarea><br>
		<input type="text" name="menuno" value="${myWork.menu.menuNo }" style="display: none;"/>
		<div class="udp">
		<button>수정</button> <button type="button" onclick="location.href='/view/mywork'" >취소</button>
		</div>
	</form>
</div>
	
</section>

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>