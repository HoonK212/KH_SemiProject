<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"  href="/resources/css/total.css" >
</head>
<body>

<div class="eval" id="evalDiv">

<c:forEach begin="0"  end="9" varStatus="status">
   
   <c:if test="${!status.last }">
      <c:if test="${status.index % 4 eq 0 }">
         <div class="ver">
         <div class="hor" id="eval${status.index }"></div>
         <div class="h_blank"></div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 1 }">
         <div class="hor" id="eval${status.index }"></div>
         <div class="h_blank" ></div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 2 }">
         <div class="hor" id="eval${status.index }"></div>
         <div class="h_blank"></div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 3 }">
         <div class="hor" id="eval${status.index }"></div>
         </div>
         <div class="v_blank"></div>
      </c:if>
   </c:if>
   
   <c:if test="${status.last }">
      <c:if test="${status.index % 4 eq 0 }">
         <div class="ver">
         <div class="hor" id="eval${status.index }"></div>
         </div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 1 }">
         <div class="hor" id="eval${status.index }"></div>
         </div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 2 }">
         <div class="hor" id="eval${status.index }"></div>
         </div>
      </c:if>
      
      <c:if test="${status.index % 4 eq 3 }">
         <div class="hor" id="eval${status.index }"></div>
         </div>
      </c:if>
   </c:if>

</c:forEach>

</div>

</body>
</html>