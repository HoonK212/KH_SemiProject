<%@page import="web.dto.Menu"%>
<%@page import="web.dto.Image"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>

   
<section class="wrapper">


<div class="submenu_back">
     <div class="submenu_list">
      <ul>
         <li class="submenu"><input type="button" class="filter_btn" value="치킨" id="food_1" /></li>
         <li class="submenu"><input type="button" class="filter_btn" value="피자" id="food_2" /></li>
         <li class="submenu"><input type="button" class="filter_btn" value="족발" id="food_3" /></li>
         <li class="submenu"><input type="button" class="filter_btn" value="패스트푸드" id="food_4" /></li>
         <li class="submenu"><input type="button" class="filter_btn" value="디저트" id="food_5" /></li>
         
         <li class="submenu" id="sub_filter"><input type="button" class="filter_btn" value="추가필터" id="sub"/></li>
      </ul>
</div>
<div>

<div>
      <ul class="sub_submenu">
            <li class="submenu" style="width: 24%"><input type="button" class="filter_btn_detail" value="평점순" id="sub_filter_1" /></li>
            <li class="submenu" style="width: 24%"><input type="button" class="filter_btn_detail" value="리뷰순" id="sub_filter_2" /></li>
            <li class="submenu" style="width: 24%"><input type="button" class="filter_btn_detail" value="가격순" id="sub_filter_3" /></li>
            <li class="submenu" style="width: 24%"><input type="button" class="filter_btn_detail" value="출시일순" id="sub_filter_4" /></li>
      </ul>
</div>

</div>
     
     
</div>


<!-- modal -->
<div class="modal mo_hidden" id="mo_wrapper">

</div>


<div class="eval" id="Diveval" style="margin-top: 80px;">
<c:forEach var="image" items="${image}" varStatus="status" >
   <c:if test="${!status.last }">
      <c:if test="${status.index % 4 eq 0 }">
         <div class="ver">
         <div class="hor" id="eval${status.index }" >
         	<div class="h_cont">${menu[status.index].menuName}</div>
         </div>
         <div class="h_blank"></div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 1 }">
         <div class="hor" id="eval${status.index }">
         	<div class="h_cont">${menu[status.index].menuName}</div>
         </div>
         <div class="h_blank" ></div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 2 }">
         <div class="hor" id="eval${status.index }">
         	<div class="h_cont">${menu[status.index].menuName}</div>
         </div>
         <div class="h_blank"></div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 3 }">
         <div class="hor" id="eval${status.index }">
         	<div class="h_cont">${menu[status.index].menuName}</div>
         </div>
         </div>
         <div class="v_blank"></div>
      </c:if>
   </c:if>
   
   <c:if test="${status.last }">
      <c:if test="${status.index % 4 eq 0 }">
         <div class="ver">
         <div class="hor" id="eval${status.index }">
         	<div class="h_cont">${menu[status.index].menuName}</div>
         </div>
         </div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 1 }">
         <div class="hor" id="eval${status.index }">
         	<div class="h_cont">${menu[status.index].menuName}</div>
         </div>
         </div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 2 }">
         <div class="hor" id="eval${status.index }">
         	<div class="h_cont">${menu[status.index].menuName}</div>
         </div>
         </div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 3 }">
         <div class="hor" id="eval${status.index }">
         	<div class="h_cont">${menu[status.index].menuName}</div>
         </div>
         </div>
      </c:if>
   </c:if>

   <script type="text/javascript">
   $("#eval${status.index }").css({
       "backgroundImage": "url(/upload/${image.imgServer })",
       "background-size" : "cover",
       "background-position-x": "center"
     });
   $("#eval${status.index }").attr("value" , "${menu[status.index].menuNo}");
   </script>

</c:forEach>

</div>


</section>



<style>
    .sub_submenu{display:none;} 
</style>




<script type="text/javascript">

$('.hor').on("click", function() {
   $('#mo_wrapper').removeClass("mo_hidden");
});

$('#mo_close').on("click", function() {
   $('#mo_wrapper').addClass("mo_hidden");
});

$('#mo_overlay').on("click", function() {
   $('#mo_wrapper').addClass("mo_hidden");
});

$("input[id^=food_]").click(function() {
      $.ajax({
         type: "POST"
         , url: "/eval/filter"
         , data: {
            food: $(this).val()
         }
         , dataType: "html"
         , success: function(result) {
            $("#Diveval").html(result);
         }
         , error: function() {
         }
      });
});

$(".hor").click(function() {
      $.ajax({
         type: "POST"
         , url: "/eval/detail"
         , data: {
            menuno : $(this).attr("value")
         }
         , dataType: "html"
         , success: function(result) {
            $("#mo_wrapper").html(result);
         }
         , error: function() {
         }
      });
});

function evalDetail(menuno) {
    $.ajax({
        type: "POST"
        , url: "/eval/detail"
        , data: {
           menuno : menuno
        }
        , dataType: "html"
        , success: function(result) {
           $("#mo_wrapper").html(result);
        }
        , error: function() {
        }
     });
}
       
 $("#sub").click(function(){
     $(".sub_submenu").toggle('slow');
 });
    
$(".filter_btn_detail").click(function() {
    $.ajax({
       type: "GET"
       , url: "/eval/filter/detail"
       , data: {
          filter : $(this).val()
       }
       , dataType: "html"
       , success: function(result) {
          $("#Diveval").html(result);
       }
       , error: function() {
       }
    });
});
</script>

<script type="text/javascript">
// 버튼에 마우스올릴때(메뉴)
$("input[id^=food_]").hover(function(){
   $(this).css("color", "#ccc");
} , function(){
   $(this).css("color", "white");
})
// 버튼에 클릭했을때(메뉴)
$("input[id^=food_]").click(function(){
   $(this).css("background-color", "#2AC1BC");
   $("input[id^=food_]").not($(this)).css("background-color" , "rgba(0,0,0,0.6)");
});

//버튼에 마우스올릴때(필터)
$("input[id^=sub_filter_]").hover(function(){
   $(this).css("color", "#ccc");
} , function(){
   $(this).css("color", "white");
})
// 버튼에 클릭했을때(필터)
$("input[id^=sub_filter_]").click(function(){
   $(this).css("background-color", "#2AC1BC");
   $("input[id^=sub_filter_]").not($(this)).css("background-color" , "rgba(0,0,0,0.4)");
});

</script>


<style type="text/css">
.filter_btn_detail{
/*    padding: 3px 3px; */
    border-radius: 3px;
    width: 95%;
    color: white;
     background-color: rgba(0,0,0,0.4);
    
    cursor: pointer;
    line-height: 30px;
    margin: 10px;
        border: 0px;
}

.h_cont{
	text-align: center;
    margin-top: 200px;
    color: white;
    background-color: rgba(0,0,0,0.2);
    line-height: 25px;
}

#sub_filter{
background-color: #2AC1BC;


}
</style>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=60a9ad83350a6fade9e1842c1765a6e7&libraries=services"></script>

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>