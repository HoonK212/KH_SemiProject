<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<c:import url="/WEB-INF/views/fran_layout/header.jsp" />

<c:import url="/WEB-INF/views/fran_layout/header_menu.jsp" />

<!-- 스마트 에디터2 라이브러리 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<!-- <form>태그의 submit 을 수행하면 SmartEditor에 작성한 내욜을 <textarea>에 반영 -->
<script type="text/javascript">
function submitContents(elClickedObj){
	
	oEditors.getById["applyMenuInfo"].exec("UPDATE_CONTENTS_FIELD",[]);
	
	try {
		elClickedObj.form.submit();
	} catch (e) {

	}
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#menu-sub").click(function() {
		
		//스마트에디터의 내용을 <textarea>에 적용하는 함수를 호출한다
		submitContents($("#sub"));
		
		//실제 <form>의 submit 수행
		$("form").submit();
	});
	
});
</script>

<style type="text/css">
#applyMenuInfo {
	width: 99%;
}
</style>




<div id="body-content-applyMenu">
<form class="form-horizontal" action="/fran/apply/menu" method="post"
 enctype="multipart/form-data">

 <fieldset>
	<legend><span class="badge"> 신메뉴 등록 신청 </span></legend>

  <div class="form-group">
    <label class="col-sm-2 control-label">브랜드 명 : </label>
    <div class="col-sm-10">
      <p class="form-control-static" >${franname }</p>
    </div>
  </div>
  
  <div class="form-group">
    <label for="inputMenuName" class="col-sm-2 control-label">신메뉴 명 : </label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="inputMenuName" placeholder="신메뉴를 입력하세요." name="applyMenuName">
    </div>
  </div>
  
  <div class="form-group">
    <label for="inputMenuCost" class="col-sm-2 control-label">출시 가격 : </label>
    <div class="col-sm-10">
      <input type="number" class="form-control" id="inputMenuCost" placeholder="출시할 가격을 입력하세요." name="applyMenuCost">
    </div>
  </div>
  
  <div class="form-group">
    <label for="inputDate" class="col-sm-2 control-label">출시 일자 : </label>
    <div class="col-sm-10">
      <input type="date" class="form-control" id="inputDate" name="applyMenuDate"> 
    </div>
  </div>
  
  
  <div class="form-group">
    <label for="inputImage" class="col-sm-2 control-label">메뉴 이미지 : </label>
    <div class="col-sm-10">
      <input type="file" id="inputImage" name="applyMenuImage">
    </div>
  </div>

  <div class="form-group">
    <label for="applyMenuInfo" class="col-sm-2 control-label">메뉴 소개글 : </label>
	<div class="col-sm-10">
	  <textarea class="form-control" rows="8" id="applyMenuInfo" name="applyMenuInfo"></textarea>
	</div>
  </div>
  
  <div class="form-group">
    <label for="chk" class="col-sm-2 control-label">이용약관 동의(필수)      <input type="checkbox" name="chk">    </label>
    <div class="col-sm-10">
        <textarea class="form-control" rows="4" id="" placeholder="여러분을 환영합니다.브랜드 평점 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 브랜드 평점 서비스의 이용과 관련하여 브랜드 평점 서비스를 제공하는 브랜드 평점 주식회사(이하 ‘브랜드 평점’)와 이를 이용하는 브랜드 평점 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 브랜드 평점 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다." readonly="readonly"></textarea>
	</div>
  </div>
  
  
  <div class="form-gruop" id="button-apply-menu">
 	 <a href="/fran"><button type="button" class="btn btn-warning" id="menu-cancle">취소</button></a>
 	 <button type="submit" class="btn btn-info" id="menu-sub">등록신청</button>
  </div>

</fieldset>
</form>

</div>

<script type="text/javascript">

var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors
	, elPlaceHolder:"applyMenuInfo" //에디터가 적용될 <textarea>의 id
	, sSkinURI: "/resources/se2/SmartEditor2Skin.html" //에디터 스킨
	, fCreator: "createSEditor2"
})

</script>








<c:import url="/WEB-INF/views/fran_layout/footer.jsp" />    