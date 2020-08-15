<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 



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




<script type="text/javascript">
$('.hor').on("click", function() {
	$('#mo_wrapper').removeClass("mo_hidden");
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
</script>



