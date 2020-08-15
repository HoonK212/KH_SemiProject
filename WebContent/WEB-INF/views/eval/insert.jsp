<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style type="text/css">
.star-box {
	/* 별과 별 사이 공백 제거 */
	font-size: 0;
}

.star {
	/* width,height 적용가능하도록 변경 */
	display: inline-block;
	/* 별이 표현되는 영역 크기 */
	width: 30px;
	height: 60px;
	/* 투명한 별 표현 */
	background-image:
		url(https://image.flaticon.com/icons/svg/159/159772.svg);
	background-repeat: no-repeat;
	background-size: 200%;
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
		url("/resources/image/star2.gif");
}

.h2only {
	/* width:130px; */
	/* margin : auto; */
	
}

.star-value {
	width: 50px;
	margin: auto;
	font-size: 20px;
}

.btndiv {
	/* text-align: center; */
	margin-top: 10px;
}
#submit_btn {
	all: unset;
	cursor: pointer;
}
#submit_btn:hover {
color:rgba(42,193,188,0.6);
}
</style>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

//별 선택 위치 변수
var idx = -1;
$(document).ready(function() {
   
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
      
      //선택한 별점 숫자를 클릭위치값으로 고정
      showStarValue(idx);
      
   })
   
   //마우스를 올리면 별 채워지기
   var hover_idx = -1;
   $(".star").mouseover(function() {
      //클릭된 별이 몇 번째 칸인지 알아내기
      hover_idx = $(this).index();
      
      //모두 투명하게 만들기
      $(".star").removeClass("on")
      
      //클릭이 된 곳까지 채워진 별로 만들기
      for(var i=0; i<=hover_idx; i++) {
         $(".star").eq(i).addClass("on");
      }
      
      //선택한 별점 숫자를 마우스움직임에 따라 변경
      showStarValue(hover_idx);
      
   })
   
   //마우스를 떼면 클릭된 값으로 초기화시키기
   $(".star").mouseout(function() {
      
      //별점 숫자를 클릭된 값으로 초기화
      showStarValue(idx);
      
      //모두 투명하게 만들기
      $(".star").removeClass("on")
      
      //클릭이 된 곳까지 채워진 별로 만들기
      for(var i=0; i<=idx; i++) {
         $(".star").eq(i).addClass("on");
      }
      
   })


    
    $("#submit_btn").click(function(){

       $.ajax({
            type: "post"
            , url: "/eval/detail/insert"
            , data: {
               review: $('#review').val(),
               starscore: $('#starscore').val(),
               menuno: $('#star_food').val()
            }
            , dataType: "html"
            , success: function(result) {
            }
            , error: function() {
            }
         });


       
       $.ajax({
            type: "post"
            , url: "/eval/detail"
            , data: {
               menuno: ${menuno }
            }
            , dataType: "html"
            , success: function(result) {
               $("#mo_wrapper").html(result);
            }
            , error: function() {
            }
         });
    });
    
   
})

//별 선택 값을 숫자로 보여주는 함수
function showStarValue(val) {
   if( val < 0) {
      val = 0;
   } else {
      val = (val+1)/2;
   }
   
   $(".star-value").html(val);
   
   var starVal = $('#star_div').text();
   document.getElementById("starscore").value = starVal;
}
</script>

</head>
<body>
	<div id="mo_right">
		<div class="h2only" style="margin: 10px 0px; width:600px; text-align: center; margin-top: 30px; font-size: 30px;">
			<H2>메뉴 평가하기</H2>
		</div>
		<div class="star-box" style="width: 600px; text-align: center; margin-top: 40px; margin-bottom: 30px;">
			<span class="star star_left"></span> <span class="star star_right"></span>
			<span class="star star_left"></span> <span class="star star_right"></span>
			<span class="star star_left"></span> <span class="star star_right"></span>
			<span class="star star_left"></span> <span class="star star_right"></span>
			<span class="star star_left"></span> <span class="star star_right"></span>
		</div>

		<div class="star-value" id="star_div" style="display: none; ">0</div>
		<span style="margin-left: 25px;">한줄평 입력</span>
		<input type="text" id="review" name="review" size="45" 
			style = "height: 150px; width: 550px; margin-left:25px; font-size: 27px;" /><br> <input type="hidden"
			id="starscore" name="starscore" /> <input type="hidden"
			id="star_food" name="food" value="${menuno }" />

		<div class="btndiv" style= "width:600px; text-align: center; margin-top: 30px; font-size: 30px;">
			<button id="submit_btn"><i class="xi-location-arrow xi-4x"></i></button>
		</div>
	</div>
	
	

