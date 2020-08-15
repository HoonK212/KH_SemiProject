<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:import url="/WEB-INF/views/layout/header.jsp"></c:import>
<c:import url="/WEB-INF/views/layout/header_slide.jsp"></c:import>

<div class="modal mo_hidden" id="mo_wrapper">
</div>

<c:import url="/WEB-INF/views/layout/body_content.jsp"></c:import>
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>

<script type="text/javascript">
$(".slidermodal").click(function(){
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
})
$(".slidermodal").on("click", function() {
   $('#mo_wrapper').removeClass("mo_hidden");
});

$('#mo_close').on("click", function() {
   $('#mo_wrapper').addClass("mo_hidden");
});

$('#mo_overlay').on("click", function() {
   $('#mo_wrapper').addClass("mo_hidden");
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
</script>