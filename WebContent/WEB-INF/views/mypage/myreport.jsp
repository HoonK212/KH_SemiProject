<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<c:import url="/WEB-INF/views/mypage/profile.jsp"></c:import>

<style type="text/css">

.myreport {
	margin-top: 262px;
	height: 700px;
	display: inline-block;
	width: 72%;
	text-align: center;
	font-size: 24px;
}

.myreport h1 {
	margin-bottom:25px;
}

.myreport hr {
	margin-top: 15px;
	margin-bottom: 30px;
}

.myreport table{
	border: 1px solid black;
	border-collapse: collapse;
	font-size: 20px;
}

.myreport th, td {
	text-align: center;
	padding: 10px;
	border: 1px solid black;
}
.myreport td {
	font-weight: normal;
}

</style>
<body>

<div class="myreport">
	<h1>신고 내역</h1>
	<hr>

	<table>
		<tr style="background-color: #2AC1BC; color: white;">
			<th style="width: 10%;"><h4>신고대상</h4></th>
			<th style="width: 15%;"><h4>신고리뷰</h4></th>
			<th style="width: 10%;"><h4>신고이유</h4></th>
			<th style="width: 10%;"><h4>상세내용</h4></th>
			<th style="width: 10%;"><h4>신고일자</h4></th>
			<th style="width: 10%;"><h4>조치내용</h4></th>
			<th style="width: 10%;"><h4>조치여부</h4></th>
		</tr>
	 	<c:forEach var="i" begin="0" end="${fn:length(Myreport)}" items="${Myreport }" >
			<tr>
			<td><h4>${i.user.userNick }</h4></td>
			<td><h4>${i.review.reviewContent }</h4></td>
			<td>
			<c:choose>
				<c:when test="${i.report.rptRsn eq 1 }">
				<h4><%="비속어 사용" %></h4>
				</c:when>
				<c:when test="${i.report.rptRsn eq 2 }">
				<h4><%="허위사실 유포" %></h4>
				</c:when>
				<c:when test="${i.report.rptRsn eq 3 }">
				<h4><%="불충분한 정보" %></h4>
				</c:when>
				<c:otherwise>
				<h4><%="기타" %></h4>
				</c:otherwise>
			</c:choose> 
			</td>
			<td><h4>${i.report.rptDetail }</h4></td>
			<td><h4>${i.report.rptDate }</h4></td>
			<td>
			<c:choose>
		        <c:when test="${i.report.rptManage eq 1 }">
		        <h4><%="계정 1일 정지" %></h4>
		        </c:when>
		        <c:when test="${i.report.rptManage eq 2 }">
		        <h4><%="계정 3일 정지" %></h4>
		        </c:when>
		        <c:when test="${i.report.rptManage eq 3 }">
		        <h4><%="계정 7일 정지" %></h4>
		        </c:when>
		        <c:when test="${i.report.rptManage eq 4 }">
		        <h4><%="계정 30일 정지" %></h4>
		        </c:when>

 		 	</c:choose>
			</td>
			<td><h4>${i.report.rptYn }</h4></td>
			</tr>
		</c:forEach>
	</table>
	</div>

</body>

</section>

<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>