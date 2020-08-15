<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height: 100%;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:250px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}
</style>

<script type="text/javascript">
$(document).ready(function() {
	//클릭된 별이 몇 번째 칸인지 알아내기
	var idx =  ${staravg } * 2 - 1;

	//모두 투명하게 만들기
	$(".star").removeClass("on")

	//클릭이 된 곳까지 채워진 별로 만들기
	for (var i = 0; i <= idx; i++) {
		$(".star").eq(i).addClass("on");
	}
})
</script>

<style type="text/css">
#mo_review div.userPic {
	display: inline-block;
	background-size: cover;
	background-position-x: center;
	width: 80px; height : 80px; border-radius : 50%;
	margin: 10%;
	border-radius: 50%;
}
</style>

<script type="text/javascript">
</script>
<script type="text/javascript">

</script>





<script type="text/javascript">
$(document).ready(function() {

var goodbad = "";
$(".goodbtn").click(function() {
	goodbad = "good";
	send_goodbad(this, goodbad);
});

$(".badbtnn").click(function() {
	goodbad = "bad";
	
	send_goodbad(this, goodbad);
});

})


function send_goodbad(th, goodbad) {
	
	var state = $(th).parents("tr").attr("data-good_bad");
	
	if( state == 0 ) {
		
		$.ajax({
	       type: "get"
	       , url: "/eval/detail/goodbad"
	       , data: {
	    	   reviewno: $(th).parents("tr").attr("data-reviewno"),
	    	   goodbad: goodbad	
	       }
	       , dataType: "html"
	       , success: function(result) {
	       }
	       , error: function() {
	       }
	    });
		
		evalDetail(${menuno });

		
		
	} else if( state == 1 ) {
		alert("이미 좋아요를 누르셨습니다");
		
	} else if( state == 2 ) {
		alert("이미 싫어요를 누르셨습니다");
	}
}
</script>







<div class="mo_overlay" id="mo_overlay"></div>
<div class="mo_content">
	<div id="mo_left">
		<div class="mo_detail" id="mo_image" style="height: 74%; border-radius: 5%;"></div>
		<div class="mo_detail mo_detail_blank"></div>
				<div class="mo_detail" id="mo_expl"
					style="background-color: rgba(42,193,188,0.6); height: 22%; color: white; 
					font-weight: bolder; border-radius: 5%;     overflow: scroll;     padding-top: 10px;
    overflow-x: auto; overflow-y: auto;"> 사진 : ${image.imgServer } 가격: ${menu.menuCost} </div>
		<div></div>
	</div>
	<div id="mo_middle"></div>
	<div id="mo_right">
		<button class="mo_button" id="mo_close">X</button>
		<div class="mo_detail" id="mo_star" style="height: 21%;     text-align: center;">
			<div class="star-box" style="    margin-bottom: 10px;
    margin-top: 20px;">
				<span class="star star_left"></span> <span class="star star_right"></span>
				<span class="star star_left"></span> <span class="star star_right"></span>
				<span class="star star_left"></span> <span class="star star_right"></span>
				<span class="star star_left"></span><span class="star star_right"></span>
				<span class="star star_left"></span> <span class="star star_right"></span>
			</div>
			<c:if test="${login }">
				<div style="    width: 80%;
    margin: 0 auto;">
				<button class="button ajaxbutton" id="ajax_star_comment" style="float: left;">별점&한줄평 등록</button>
					<input type="text" placeholder="지역명 입력" id="map_in" style=" margin-left: 80px;
    line-height: 25px; "/>
					<button id="map_btn" class="button">지점검색</button>
				</div>
			</c:if>
			<c:if test="${!login or empty login }">
				<div>로그인 하시면 평가 등록 등 추가기능을 사용하실 수 있습니다.</div>
			</c:if>


			<input type="hidden" value="${revcnt }" id="revcnt" />
		</div>



		<div class="mo_detail" id="mo_review"
			style=" height: 70%; overflow: auto;     margin-top: 20px;">
			<table style="margin-left: 17px;">
				<c:forEach var="item" items="${reviewList}" varStatus="status">
					<tr data-reviewno="${item.review.reviewNo }"
						data-good_bad="${item.reviewverif.good_bad }">
						<td><div class="userPic"
								style="background-image: url('/upload_picture/${item.picture.picServer }');"></div></td>
						<td width="100%" style="padding: 20px"><span>${item.review.reviewContent }</span></td>
						<td style="text-align: center;">
							<button class="goodbtn">
								<i class="xi-emoticon-smiley-o xi-2x"></i>
							</button> <span style="font-size: 10px">${item.review.reviewGood }</span>
						</td>
						<td style="text-align: center;">
							<button class="badbtnn">
								<i class="xi-emoticon-devil xi-2x"></i>
							</button> <span style="font-size: 10px">${item.review.reviewBad }</span>
						</td>
						<c:if test="${not empty login }">
							<td><button class="ajax_report"
									value="${item.review.reviewNo }"
									target="${item.review.userNo }"
									content="${item.review.reviewContent }">
									<img src = "https://image.flaticon.com/icons/svg/149/149196.svg" class="report_img" style="width: 25px;"></button>
									<span></span></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</div>
		</div>











	</div>

	<script type="text/javascript">
	$('#mo_close').on("click", function() {
		$('#mo_wrapper').addClass("mo_hidden");
	});
	$('#mo_overlay').on("click", function() {
		$('#mo_wrapper').addClass("mo_hidden");
	});
</script>
<script type="text/javascript">
	$("#mo_image").css({
		"background-image" : "url(/upload/${image.imgServer })",
		"background-size" : "cover",
		"background-position-x" : "center"
	});

	$("#mo_expl").html("<p style=\"padding-left: 11px;\">가격 : ${menu.menuCost} 원</p> <p style=\"padding-left: 11px;\">출시일 : ${menu.menuDate}</p> <p style=\"padding-left: 11px;\">메뉴정보 : ${menu.menuInfo}</p>");
</script>
<script type="text/javascript">
	       
 $(".ajaxbutton").click(function() {
 			if ( $(revcnt).val() == 1) {
	        	 alert("이미 한줄평을 쓰셨습니다 한줄평 수정은 마이페이지에서 가능합니다");
	         } else {
	        	 $("#mo_image").css({
	       	      "background-image" : "url(/upload/${image.imgServer })",
	       	      "background-size" : "cover",
	       	      "background-position-x" : "center"
	       	   }); 
	       	         $.ajax({
	       	            type: "get"
	       	            , url: "/eval/detail/insert"
	       	            , data: {
	       	               menuno: ${menuno },
	       	               revcnt:${revcnt }
	       	            }
	       	            , dataType: "html"
	       	            , success: function(result) {
	       	               $("#mo_right").html(result);
	       	            }
	       	            , error: function() {
	       	            }
	       	         });
	       	         
	       	         
	         }
 			
	   });

 
 $(".ajax_report").click(function(){
    $.ajax({
          type: "get"
          , url: "/eval/detail/report"
          , data: {
                menuno : ${menuno },
                reviewno : $(this).val(),
                targetno : $(this).attr("target"),
                content : $(this).attr("content")
          }
          , dataType: "html"
          , success: function(result) {
             $("#mo_right").html(result);
          }
          , error: function() {
          }
       });
 });
 
	</script>


	<style type="text/css">
.star-box {
	/* 별과 별 사이 공백 제거 */
	font-size: 0;
}

.star {
	/* width,height 적용가능하도록 변경 */
	display: inline-block;
	/* 별이 표현되는 영역 크기 */
	width: 50px;
	height: 90px;
	/* 투명한 별 표현 */
	background-image:
		url("/resources/image/star33.gif");
	background-repeat: no-repeat;
	background-size: 200%;
}
.ajax_report{
width:30px;
height:30px;
 all: unset; 
 cursor: pointer; 
}
.report_img{
    width: 25px;
    margin-left: 15px;
    margin-right: 5px;

}
.star_left {
	/* 왼쪽 별 */
	background-position: 0 0;
}

.star_right {
	/* 오른쪽 별 */
	background-position: 100% 0;
}

.on {
	/* 채워진 별로 이미지 변경 */
	background-image:
		url("/resources/image/star44.gif");
}

.star-value {
	width: 50px;
	/* 	margin-left: 550px; */
	font-size: 20px;
}

#ajax_star_comment {
	
		display: inline-block;
	width: 120px;
	text-align: center;
	text-decoration: none;
	line-height: 27px;
	outline: none;

border-color: inherit;
	
}

.goodbtn {
	all: unset;
	cursor: pointer;
}

.badbtnn {
	all: unset;
	cursor: pointer;
}


.button {
	display: inline-block;
	width: 80px;
	text-align: center;
	text-decoration: none;
	line-height: 27px;
	outline: none;
border-color: inherit;
}
.button::before,
.button::after {
	position: absolute;
	z-index: -1;
	display: block;
	content: '';
}
.button,
.button::before,
.button::after {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	-webkit-transition: all .3s;
	transition: all .3s;
}

.button {
	background-color: #6d819c;
	background-color: #2AC1BC;
	
	color: #fff;
	font-weight: bolder;
}


	
</style>

<script>
$("#map_btn").click(function() {
      $.ajax({
         type: "GET"
         , url: "/eval/map"
         , data: {
        	menuno: ${menuno },
            where: $('#map_in').val()
         }
         , dataType: "html"
         , success: function(result) {
            $(".mo_content").html(result);
            
         }
         , error: function() {
         }
      });
});
</script>