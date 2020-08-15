<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	Date nowDate = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
%>


    
<c:import url="/WEB-INF/views/fran_layout/header.jsp" />

<c:import url="/WEB-INF/views/fran_layout/header_menu.jsp" />

<div id="body-content-applyBlind">
<form class="form-horizontal" action="/fran/apply/blind" method="post">

 <fieldset>
	<legend><span class="badge"> blind 메뉴 신청 </span></legend>

  <div class="form-group">
    <label class="col-sm-2 control-label">브랜드 명 : </label>
    <div class="col-sm-10">
      <p class="form-control-static">${franname }</p>
    </div>
  </div>
  
  <div class="form-group">
    <label for="inputblindName" class="col-sm-2 control-label">메뉴 명 : </label>
    <div class="col-sm-10">
    
      <select class="form-control" name="applyblindNameByMenuNo">
		<option value="" selected="selected">메뉴선택</option>
			<c:forEach items="${MenuList }" var="menu">
				<option value="${menu.menuNo}" id="inputblindName">${menu.menuName }</option>
			</c:forEach>
      </select>
      
    </div>
  </div>
  
  
  <div class="form-group">
    <label for="inputblindStart" class="col-sm-2 control-label">요청(오늘) 일자 : </label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputblindStart" name="inputblindStart" readonly="readonly" placeholder="현재(오늘) 날짜로 처리됩니다." >
    </div>
  </div>

  <div class="form-group">
    <label for="inputblindDate" class="col-sm-2 control-label">처리(희망) 일자 : </label>
    <div class="col-sm-10">
      <input type="date" class="form-control" id="inputblindDate" name="applyblindDate">
    </div>
  </div>

  
   <div class="form-group">
    <label for="applyBlindRsn" class="col-sm-2 control-label">등록 사유 : </label>
	<div class="col-sm-10">
	  <textarea class="form-control" rows="6" id="applyBlindRsn" name="applyBlindRsn"></textarea>
	</div>
  </div>
  
  
  <div class="form-group">
    <label for="blind-message" class="col-sm-2 control-label">비고 : </label>
	<div class="col-sm-10">
	  <textarea class="form-control" rows="4" id="blind-message" name="blind-message"></textarea>
	</div>
  </div>
  
  <div class="form-group">
    <label for="chk" class="col-sm-2 control-label">이용약관 동의(필수)      <input type="checkbox" name="chk">    </label>
    <div class="col-sm-10">
        <textarea class="form-control" rows="4" id="" placeholder="여러분을 환영합니다.브랜드 평점 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 브랜드 평점 서비스의 이용과 관련하여 브랜드 평점 서비스를 제공하는 브랜드 평점 주식회사(이하 ‘브랜드 평점’)와 이를 이용하는 브랜드 평점 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 브랜드 평점 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다." readonly="readonly"></textarea>
	</div>
  </div>
  
  
  <div class="form-gruop" id="button-apply-blind">
  	  <a href="/fran"><button type="button" class="btn btn-warning" id="blind-cancle">취소</button></a>
 	 <button type="submit" class="btn btn-info" id="blind-sub">등록신청</button>
  </div>

</fieldset>
</form>

</div>


<c:import url="/WEB-INF/views/fran_layout/footer.jsp" />    