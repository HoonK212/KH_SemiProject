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
	width: 15px;
    height: 30px;

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

.mywork {
	margin-top: 262px;
 	min-height: 700px; 
	display: inline-block;
	width: 72%;
	text-align: center;
	font-size: 24px;
}

.mywork h1 {
	margin-bottom:25px;
}

.mywork hr {
	margin-top: 15px;
	margin-bottom: 30px;
}

.mywork table{
	border: 1px solid black;
	border-collapse: collapse;
	font-size: 20px;
}

.mywork th, td {
	text-align: center;
	padding: 10px;
	border: 1px solid black;
}

.mywork td {
	font-weight: normal;
}

</style>


<script type="text/javascript">
$(document).ready(function() {

	ss();
	
	$('.delete_btn').click(function() {
	    var dBtnRes = confirm('정말 삭제하시겠습니까?');
	    console.log(dBtnRes);
	    
	    if(dBtnRes) {
	    	$(this).next().click();
	    }
	});
	
})

function ss() {
	
	$(".star-box").each(function() {
		
		$(this).find(".star").removeClass("on")
		
		for(var i=0; i<= $(this).attr("data-starScore")*2-1; i++){
			$(this).find(".star").eq(i).addClass("on");
		}

	});
}
</script>

	<div class="mywork">
	<h1>리뷰 관리</h1>
	<hr>
	<table>
		<tr style="background-color: #2AC1BC; color: white;">
			<th style="width: 10%;"><h4>메뉴</h4></th>
			<th style="width: 10%;"><h4>별명</h4></th>
			<th style="width: 20%;"><h4>별점</h4></th>
			<th style="width: 20%;"><h4>리뷰</h4></th>
			<th style="width: 15%;"><h4>별점작성일</h4></th>
			<th style="width: 15%;"><h4>리뷰작성일</h4></th>
			<th style="width: 10%;"><h4>관리</h4></th>
		</tr>	
	 	<c:forEach var="i" items="${myworkList }"> 
		<tr>
			<td><h4>${i.menu.menuName }</h4></td>
			<td><h4>${i.user.userNick }</h4></td>
			<td>
			<div class="star-box" data-starScore="${i.star.starScore }">
			<h4>
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
			</h4>
			</div>
			</td>
			<td><h4>${i.review.reviewContent }</h4></td>
			<td><h4>${i.star.starDate }</h4></td>
			<td><h4>${i.review.reviewDate }</h4></td>
			<td>
			<button><h3><a href="/update/mywork?menuNo=${i.menu.menuNo }">수정</a><h3></button> 
			<button class="delete_btn"><h3>삭제</h3></button>
			<button style="display: none;" onclick="location.href='/delete/mywork?menuNo=${i.menu.menuNo }'"></button>
			</td>
		</tr>
	 	</c:forEach> 
	</table>
	</div>
	
</section>

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>