<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style type="text/css">
    .xi-location-arrow:hover{
	color:rgba(42,193,188,0.6);
	}
    
    </style>
<body>
<div style = "width:600px; margin-left: 30px; margin-top: 50px; margin-bottom: 20px;">
<h1 style="color: teal;">신고할 한줄평</h1>
<br>
<span style="font-size: 20px; color: teal;">내용 :</span>
<span style="font-size: 20px;">  ${content }</span>
</div>

<hr style="  height:0px;
  border-radius: 2px;
  margin-left: 30px; 
  color: teal;
  border: 2px solid currentColor;
  width: 90%;">

<div style ="width:600px; margin-left: 30px; margin-top: 20px;">
<h1 style="color: teal;">신고 작성</h1>
<br>
</div> 

<div style ="width:600px; text-align: center;">
<input type="hidden" value="${loginUserNo }" id="loginUserNo">
<input type="hidden" value="${targetno }" id="targetNo">
<input type="hidden" value=" ${content }" id="content">
<input type="hidden" value="${reviewno }" id="reviewNo">
</div>
<%-- 신고자 번호 : ${loginUserNo }<br> --%>
<%-- 대상자 번호 : ${targetno } <br> --%>
<%-- 대상자 한줄평 : ${content }	<br> --%>
<%-- 리뷰번호 : ${reviewNo }<br> --%>
<div style = "width:600px; text-align: left; font-size :20px; margin-left: 30px; " >
<label for="report_reason"  style="color: teal;">신고 이유 : </label>
<select id="report_reason" style="font-size: 16px;">
	<option value="1">비속어 사용</option>
	<option value="2">허위사실 유포</option>
	<option value="3">불충분한 정보</option>
	<option value="4">기타</option>
</select>
</div>
<div style = "width:600px; text-align: left; font-size: 20px; margin-left: 30px; ">
<label for="report_content"  style="color: teal;">상세 내용 : </label>
</div>
<textarea rows="5" cols="47" id="report_content" style = "font-size:18px; margin-left: 30px; "></textarea>
<div style= "width:600px; text-align: center; margin-top: 20px; font-size: 30px; display:inline-block;" class="reportbtn">
<button id="submit_report" style = "all:unset; cursor: pointer;"><i class="xi-location-arrow xi-4x"></i></button>
</div>
<script>

    $("#submit_report").click(function(){

       $.ajax({
            type: "post"
            , url: "/eval/detail/report"
            , data: {
            	report_reason: $('#report_reason').val(),
            	report_content: $('#report_content').val(),
            	loginUserNo: $('#loginUserNo').val(),
            	targetNo: $('#targetNo').val(),
            	reviewNo: $('#reviewNo').val()
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

</script>

